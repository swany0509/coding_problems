from sys import stdin

li = set()
ssum = -100

for _ in range(9) :
    tmp = int(stdin.readline())
    li.add(tmp)
    ssum += tmp

nan = set()
for i in li:
    if ssum-i in li :
        nan = {i,ssum-i}

for j in li-nan:
    print(j)
