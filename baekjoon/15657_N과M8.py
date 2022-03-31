from sys import stdin
from itertools import combinations_with_replacement

N,comb = map(int,stdin.readline().split())

data = list(map(int,stdin.readline().split()))
data.sort()
li = list(combinations_with_replacement(data,comb))

for i in li:
    for j in i:
        print(j,end=" ")
    print()