from sys import stdin

N = int(stdin.readline())
data = list(map(int,stdin.readline().split()))

data.sort()
if N%2==1: print(data[N//2]**2)
else : print(data[0]*data[N-1])