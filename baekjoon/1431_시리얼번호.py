from sys import stdin

data = {}
for _ in range(int(stdin.readline().rstrip())):
    ss = stdin.readline().rstrip()
    l = len(ss)
    cnt = 0
    for i in ss:
        if i.isdigit() : cnt += int(i)

    if l not in data.keys(): data[l] = [[cnt,ss]]
    else : data[l].append([cnt,ss])

for k in sorted(data.keys()):
    for dd in sorted(data[k]): print(dd[1])