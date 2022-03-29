from sys import stdin
from itertools import permutations

N = int(stdin.readline())
arr = list(map(int,stdin.readline().split()))
data = list(permutations(arr,N))
dp = []

for target in data:
    sum = 0
    for i in range(N-1):
        sum+=abs(target[i]-target[i+1])
    dp.append(sum)

print(max(dp))