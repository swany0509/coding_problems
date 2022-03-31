from collections import deque
from sys import stdin

dx = [-1, -2, -2, -1, 1, 2, 2, 1]
dy = [2, 1, -1, -2, -2, -1, 1, 2]

def bfs(ax,ay,bx,by):
    q = deque()
    q.append([ax,ay])
    li[ax][ay] = 1
    while(q):
        a,b = q.popleft()
        if a==bx and b ==by:
            print(li[bx][by]-1)
            return
        for i in range(8):
            x=a+dx[i]
            y=b+dy[i]
            if 0 <= x < L and 0 <= y < L and li[x][y] == 0:
                q.append([x, y])
                li[x][y] = li[a][b] + 1

for i in range(int(stdin.readline())):
    L = int(stdin.readline())
    li = [[0]*L for _ in range(L)]

    ax,ay = map(int,stdin.readline().split())
    bx,by = map(int,stdin.readline().split())

    bfs(ax,ay,bx,by)