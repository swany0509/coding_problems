from sys import stdin
N,M = map(int,stdin.readline().rstrip().split())
prices = sorted([int(stdin.readline().rstrip()) for _ in range(M)],reverse=True)
ans = max([[(i+1)*prices[i],prices[i]] for i in range(min(N,M))])
print(ans[1],ans[0])