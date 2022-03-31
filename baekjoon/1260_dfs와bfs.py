from sys import stdin

N,M,V = map(int,stdin.readline().split())
visit = [0 for i in range(N+1)]
li = [[0 for __ in range(N+1)] for _ in range(N+1)]

for i in range(M):
    a,b = map(int,stdin.readline().split())
    li[a][b]=1
    li[b][a]=1

def dfs(v):
    print(v,end=' ')
    visit[v]=1
    for i in range(1,N+1):
        if visit[i]==0 and li[v][i]==1:
            dfs(i)

def bfs(v):
    queue = [v]
    visit[v] = 0
    while(queue):
        v=queue[0]
        print(v, end=' ')
        del queue[0]
        for i in range(1,N+1):
            if visit[i]==1 and li[v][i]==1:
                queue.append(i)
                visit[i]=0

def li_print(li):
    for s in li:
        print(*s)

dfs(V)
print()
bfs(V)

li_print(li)
