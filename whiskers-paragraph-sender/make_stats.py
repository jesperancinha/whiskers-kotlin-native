cases = ['cloudnative', 'graalvm', 'jvm', 'ktor']

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
