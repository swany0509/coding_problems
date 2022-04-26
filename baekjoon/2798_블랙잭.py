from sys import stdin
from itertools import combinations

N,M = map(int,stdin.readline().split())
cards = list(map(int,stdin.readline().split()))
score = 0

ways = list(combinations(cards,3))

for nn in ways :
    ss = sum(nn)
    if ss > score and ss <= M : score=ss

print(score)

