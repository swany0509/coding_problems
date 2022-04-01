from sys import stdin
import math

def dist( x1,y1,x2,y2):
    return ((x2-x1)**2+(y2-y1)**2)

for _ in range(int(stdin.readline())) :
    x1,y1,r1,x2,y2,r2 = map(int,stdin.readline().split())
    d = dist(x1,y1,x2,y2)
    r = (r1+r2)**2
    rs = (r1+r2)**2
    rm = (abs(r1-r2))**2
    if d==0.0:
        if r1==r2 : print(-1)
        else : print(0)
    else :
        if d==rs or d==rm : print(1)
        elif d < rs and d > rm : print(2)
        else : print(0)

