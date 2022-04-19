from sys import stdin

dp = [0 for _ in range(1000054)]
for i in range(1,1000000):
    n=i
    s=n
    while n>0 :
        s+=n%10
        n=n//10
    if dp[s] == 0 : dp[s] = i

print(dp[int(stdin.readline())])