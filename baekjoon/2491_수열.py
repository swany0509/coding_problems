from gettext import dpgettext
from sys import stdin
N = int(stdin.readline().rstrip())
data = list(map(int,stdin.readline().rstrip().split()))
dp=[0,0,0,0]
prev = data[0]
for s in data:
    if prev < s: 
        dp[0] += 1
        dp[1] = max(dp[1],dp[0])
        dp[2] = 1
    elif prev == s:
        dp[0] += 1
        dp[1] = max(dp[1],dp[0])
        dp[2] += 1
        dp[3] = max(dp[3],dp[2])
    else : 
        dp[0] = 1
        dp[2] += 1
        dp[3] = max(dp[3],dp[2])
    prev=s
print(max(dp[1],dp[3]))