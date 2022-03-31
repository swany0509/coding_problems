import sys
sys.setrecursionlimit(10000)

N,M= map(int,sys.stdin.readline().split())
visit = [0 for i in range(N+1)]
li = [[0 for __ in range(N+1)] for _ in range(N+1)]

for i in range(M):
    a,b = map(int,sys.stdin.readline().split())
    li[a][b]=1
    li[b][a]=1

def dfs(v):
    visit[v]=1
    for i in range(1,N+1):
        if visit[i]==0 and li[v][i]==1:
            dfs(i)

cnt=0
for i in range(1,N+1):
    if visit[i]==0:
        dfs(i)
        cnt+=1
print(cnt)
