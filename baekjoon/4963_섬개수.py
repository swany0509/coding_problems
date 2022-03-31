from collections import deque
from sys import stdin

dx = [-1,0,0,1,1,1,-1,-1]
dy = [0,1,-1,0,1,-1,1,-1]

def bfs(a,b):
    global w,h
    global li
    queue = deque()
    queue.append([a,b])
    li[a][b] = 0

    while queue:
        x,y = queue.popleft()
        for i in range(8):
            xx = x+dx[i]
            yy = y+dy[i]
            if 0 <= xx < h and 0 <= yy < w and li[xx][yy] == 1:           
                li[xx][yy]=0
                queue.append([xx,yy])


while True:
    count=0
    w,h = map(int,stdin.readline().split())
    if w==0 : break
    li=[]
    for _ in range(h):
        li.append([int(i) for i in stdin.readline().split()])

    for i in range(h):
        for j in range(w):
            if li[i][j]==1:
                bfs(i,j)
                count+=1

    print(count)
