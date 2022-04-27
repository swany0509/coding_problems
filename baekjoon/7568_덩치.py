from sys import stdin

data = []
res = {}

N = int(stdin.readline().strip())

for i in range(N):
    data.append(list(map(int,stdin.readline().strip().split())))
    res[i] = 1

for i in range(N):
    for j in range(N):
        if i==j : continue
        if data[i][0] < data[j][0] and data[i][1] < data[j][1] : res[i]+=1


print(" ".join([str(i) for i in res.values()]))
