cases = ['cloudnative', 'graalvm', 'jvm', 'ktor', 'ktor-no-db']
title = "# Whiskers Performance results\n"
header = '| Architecture | Sartup time (seconds) | Memory used(Mbytes) | Test with DB connection(s) | Test Mixed DB + ' \
         'Algorithm(s) | Test Algorithm (s) |\n'
separator = '|---|---|---|---|---|---|\n'
f = open("../Results.md", "w")
f.write(title)
f.write("\n")
f.write(header)
f.write(separator)
for case in cases:
    with open("../result-{0}.csv".format(case)) as startupTS:
        startups = startupTS.read().splitlines()
        print(startups)
    with open("../result-test-{0}.csv".format(case)) as callsTS:
        calls = callsTS.read().splitlines()
        print(calls)
    with open("../result-mem-{0}.txt".format(case)) as usedMems:
        mems = usedMems.read().splitlines()
        print(mems)
    startupTime = int(startups[1].split(',')[1]) - int(startups[0].split(',')[1])
    memUsage = mems[1].split('   ')[3]
    timeDbConn = int(calls[1].split(',')[1]) - int(calls[0].split(',')[1])
    timeMix = int(calls[3].split(',')[1]) - int(calls[2].split(',')[1])
    timeAlgorithm = int(calls[5].split(',')[1]) - int(calls[4].split(',')[1])
    f.write("|{0}|{1}|{2}|{3}|{4}|{5}|\n".format(case, startupTime, memUsage, timeDbConn, timeMix, timeAlgorithm))
f.close()
