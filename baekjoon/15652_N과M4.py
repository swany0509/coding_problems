from sys import stdin
from itertools import combinations_with_replacement

N,comb = map(int,stdin.readline().split())

data = [i+1 for i in range(N)]
li = list(combinations_with_replacement(data,comb))

for i in li:
    for j in i:
        print(j,end=" ")
    print()