from sys import stdin
from itertools import product

N,comb = map(int,stdin.readline().split())

data = list(map(int,stdin.readline().split()))
data.sort()
li = list(set(product(data,repeat=comb)))
li.sort()

for i in li:
    for j in i:
        print(j,end=" ")
    print()