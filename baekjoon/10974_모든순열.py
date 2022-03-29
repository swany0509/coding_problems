from sys import stdin
from itertools import permutations

N = int(stdin.readline())

data = [i+1 for i in range(N)]
li = list(permutations(data,N))

for i in li:
    for j in i:
        print(j,end=" ")
    print()