from sys import stdin

data = dict()

for _ in range(int(stdin.readline())):
    ss = stdin.readline().strip()
    if len(ss) not in data.keys() :
        data[len(ss)] = [ss]
    else:
        if ss in data[len(ss)] : continue
        data[len(ss)].append(ss)


for s in sorted(data.keys()):
    for ss in sorted(data[s]) : print(ss)
