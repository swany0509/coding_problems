from sys import stdin

for i in range(int(stdin.readline())):
    N,M = map(int,stdin.readline().split())
    if M-N <= N//2: N=M-N
    S=1
    for j in range(1,N+1):
        S=S*M/j
        M-=1
    print(int(S))

