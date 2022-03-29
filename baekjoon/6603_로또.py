from sys import stdin
from itertools import combinations

while True:
    data = list(map(int,stdin.readline().split()))
    if data[0]==0 : break

    arr = data[1:]
    lottos = list(combinations(arr,6))

    for i in lottos:
        for j in i:
            print(j,end=" ")
        print()
    
    print()