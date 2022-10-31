def process_container_cases():
    for case in cases:
        with open("../result-{0}.csv".format(case)) as startup_ts:
            startups = startup_ts.read().splitlines()
            print(startups)
        with open("../result-test-{0}.csv".format(case)) as calls_ts:
            calls = calls_ts.read().splitlines()
            print(calls)
        with open("../result-mem-{0}.txt".format(case)) as used_mems:
            mems = used_mems.read().splitlines()
            print(mems)
        startup_time = int(startups[1].split(',')[1]) - int(startups[0].split(',')[1])
        mem_usage = mems[1].split('   ')[3]
        time_db_conn = int(calls[1].split(',')[1]) - int(calls[0].split(',')[1])
        time_mix = int(calls[3].split(',')[1]) - int(calls[2].split(',')[1])
        time_algorithm = int(calls[5].split(',')[1]) - int(calls[4].split(',')[1])
        f.write(
            "|{0}|{1}|{2}|{3}|{4}|{5}|\n".format(case, startup_time, mem_usage, time_db_conn, time_mix, time_algorithm))


def process_container_less_cases():
    for case in cases_container_less:
        print("Current case is {0}".format(case))
        with open("../result-no-container-{0}.csv".format(case)) as startup_ts:
            startups = startup_ts.read().splitlines()
            print(startups)
        with open("../result-test-no-container-{0}.csv".format(case)) as calls_ts:
            calls = calls_ts.read().splitlines()
            print(calls)
        with open("../result-no-container-{0}.csv".format(case)) as used_mems:
            mems = used_mems.read().splitlines()
            print(mems)
        startup_time = int(startups[1].split(',')[1]) - int(startups[0].split(',')[1])
        mem_usage = "{0}K".format(mems[2].split(',').pop().lstrip().rstrip())
        time_db_conn = int(calls[1].split(',')[1]) - int(calls[0].split(',')[1])
        time_mix = int(calls[3].split(',')[1]) - int(calls[2].split(',')[1])
        time_algorithm = int(calls[5].split(',')[1]) - int(calls[4].split(',')[1])
        f.write(
            "|{0}|{1}|{2}|{3}|{4}|{5}|\n".format(case, startup_time, mem_usage, time_db_conn, time_mix, time_algorithm))


def process_runner_tests():
    for case in cases_runners:
        print("Current case is {0}".format(case))
        with open("../result-single-run-{0}.csv".format(case)) as runs_ts:
            runs = runs_ts.read().splitlines()
        runtime = int(runs[1].split(',')[1]) - int(runs[0].split(',')[1])
        f.write(
            "|{0}|{1}|\n".format(case, runtime))


cases = ['cloudnative', 'graalvm', 'jvm', 'ktor', 'ktor-no-db']
cases_container_less = ['graalvm', 'jvm', 'ktor', 'ktor-no-db']
cases_runners = ['graalvm', 'knative', 'native']
title = "# Whiskers Performance results\n"
enterprise = "## Service test results\n"
header = '| Architecture | Startup time (seconds) | Memory used(Mbytes) | Test with DB connection(s) | Test Mixed DB + ' \
         'Algorithm(s) | Test Algorithm (s) |\n'
separator = '|---|---|---|---|---|---|\n'
runners = "## Runner test results\n"
runner_headers = '| Architecture | Runtime |\n'
runner_separator = '|---|---|\n'
f = open("../Results.md", "w")
f.write(title)
f.write("\n")
f.write(enterprise)
f.write("\n")
f.write(header)
f.write(separator)

process_container_cases()
process_container_less_cases()

f.write("\n")
f.write(runners)
f.write("\n")
f.write(runner_headers)
f.write(runner_separator)

process_runner_tests()

f.close()
