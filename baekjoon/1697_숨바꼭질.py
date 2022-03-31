from sys import stdin

n = 0
def bfs(n,k):
    visited={i:0 for i in range(100001)}
    queue = [(n,0)]
    while queue:
        n = queue.pop(0)
        num=n[0]
        if num == k : break
        if num<0 or num>100000 : continue
        if visited[num]==1 : continue
        visited[num]=1
        for nn in [num*2, num-1, num+1] : queue.append((nn,n[1]+1))
    return n[1]

N,K = map(int,stdin.readline().rstrip().split())
print(bfs(N,K))