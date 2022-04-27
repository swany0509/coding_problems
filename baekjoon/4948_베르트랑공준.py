from sys import stdin
import math

prime = [0,0]+[1 for _ in range(246913)]

for i in range(2,int(math.sqrt(246913))+1):
    if prime[i]==0 : continue
    j=i
    while j <= 246913:
        j+=i
        if j > 246913 : break
        prime[j]=0
        

N = int(stdin.readline())
while(N!=0):
    print(prime[N+1:2*N+1].count(1))
    N = int(stdin.readline())