from sys import stdin

N,M = map(int,stdin.readline().rstrip().split())
S = [stdin.readline().rstrip() for _ in range(N)]
test = [stdin.readline().rstrip() for _ in range(M)]

dic = {}

for ss in S:
    for i in range(1,len(ss)+1): dic[ss[:i]] = 1

cnt = 0
for t in test:
    try:
        cnt += dic[t]
    except KeyError:
        pass

print(cnt)