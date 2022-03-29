from sys import stdin
from itertools import combinations

N = int(stdin.readline())
N_s = [i for i in range(N)]
data = []

for _ in range(N):
    data.append(list(map(int,stdin.readline().split())))

def cal_score(tu):
    score = 0
    base = list(combinations(list(tu),2))
    for t in base:
        score+=data[t[0]][t[1]] + data[t[1]][t[0]] 
    return score

cases = list(combinations(N_s,N//2))
min_value = 100000000000000000000

for tu in cases[:len(cases)//2]:
    min_value = min(min_value,abs(cal_score(tu)-cal_score(tuple(set(N_s)-set(tu)))))

print(min_value)