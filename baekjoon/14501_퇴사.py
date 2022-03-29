from sys import stdin
from itertools import combinations

N = int(stdin.readline())
t=[]
p=[]

for _ in range(N):
    n,d = map(int,stdin.readline().split())
    t.append(n)
    p.append(d)

num = [i for i in range(N)]

dp = []
for i in range(1,N+1):
    dp += list(combinations(num,i))
maxx = 0

for ss in dp:
    ans = 0
    data = True
    cnt=0
    for i in range(N):
        if i in ss :
            if cnt>0 or i+t[i] > N:
                data=False
                break
            cnt = t[i]
        cnt-=1
    if data:
        for i in ss:
            ans += p[i]
        maxx = max(maxx,ans)

print(maxx)