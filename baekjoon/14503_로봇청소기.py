from sys import stdin
N,M = map(int,stdin.readline().rstrip().split())
r,c,d = map(int,stdin.readline().rstrip().split())
data = [stdin.readline().rstrip().split() for _ in range(N)]

clean = 0
i,j = r,c
rotate = 0
direc = [[0,-1],[-1,0],[0,1],[1,0]]
back = [[1,0],[0,-1],[-1,0],[0,1]]
finished=False

while True:
    if data[i][j]=='0':
        data[i][j]='2'
        clean+=1
    lefti,leftj = i+direc[d][0],j+direc[d][1]
    if (data[lefti][leftj]=='0'):
        rotate = 0
        if d==0: d=3
        else : d -= 1
        i,j = lefti,leftj
    else:
        rotate += 1
        if d==0: d=3
        else : d -= 1
        if rotate == 4:
            rotate = 0
            backi,backj = i+back[d][0],j+back[d][1]
            if data[backi][backj]=='1':
                finished = True
            else:
                i,j = backi,backj
    if finished : break
print(clean)