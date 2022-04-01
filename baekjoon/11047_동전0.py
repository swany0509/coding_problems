from sys import stdin

N,K = map(int,stdin.readline().rstrip().split())
coins = sorted([int(stdin.readline().rstrip()) for _ in range(N)])
cnt = 0
while(K>0):
    if not coins:
        break
    target = coins.pop()
    if K>=target:
        nn = K//target
        K-=nn*target
        cnt+=nn

print(cnt)