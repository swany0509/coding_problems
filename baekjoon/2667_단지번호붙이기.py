from collections import deque

dx = [-1,0,0,1]
dy = [0,1,-1,0]

def bfs(a,b):
    global li
    n=len(li)
    queue = deque()
    queue.append([a,b])
    li[a][b] = 0
    count=1

    while queue:
        x,y = queue.popleft()
        for i in range(4):
            xx = x+dx[i]
            yy = y+dy[i]
            if xx < 0 or xx >= n or yy < 0 or yy >= n: continue
            if li[xx][yy] == 1:            
                li[xx][yy]=0
                queue.append([xx,yy])
                count+=1
    return count

li=[]
N=int(input())
for _ in range(N):
    li.append(list([int(i) for i in [*input()]]))

cnt=[]
for i in range(N):
    for j in range(N):
        if li[i][j]==1:
            cnt.append(bfs(i,j))

cnt.sort()
print(len(cnt))
for _ in range(len(cnt)): print(cnt[_])