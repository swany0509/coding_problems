from sys import stdin
from itertools import permutations

N,comb = map(int,stdin.readline().split())

data = list(map(int,stdin.readline().split()))
data.sort()
li = list(permutations(data,comb))

for i in li:
    for j in i:
        print(j,end=" ")
    print()