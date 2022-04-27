from sys import stdin
import math
max_length=10001

prime = [0,0]+[1 for _ in range(max_length)]

for i in range(2,int(math.sqrt(max_length))+1):
    if prime[i]==0 : continue
    j=i
    while j <= max_length:
        j+=i
        if j > max_length : break
        prime[j]=0
        

for _ in range(int(stdin.readline())):
    N = int(stdin.readline())
    part = prime[:N]
    for j in range(N//2,1,-1):
        if part[j]!=0 and part[N-j]!=0:
            print(j,N-j)
            break
