from sys import stdin
from math import gcd


for _ in range(int(stdin.readline())):
    M,N,x,y = map(int,stdin.readline().split())
    maxN = M*N//gcd(M,N)

    idxM = [x+i for i in range(0,maxN,M)]

    idxN = [y+i for i in range(0,maxN,N)]
    
    ans = list(set(idxM)&set(idxN))
    if ans : print(ans[0])
    else : print('-1')
