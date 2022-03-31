from sys import stdin
from itertools import combinations

N,comb = map(int,stdin.readline().split())

data = [i+1 for i in range(N)]
li = list(combinations(data,comb))

for i in li:
    for j in i:
        print(j,end=" ")
    print()