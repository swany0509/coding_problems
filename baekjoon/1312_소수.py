from sys import stdin
a,b,n = map(int,stdin.readline().strip().split())

a%=b
for _ in range(n-1) : a=(a*10)%b 
print((a*10)//b)