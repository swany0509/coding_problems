from sys import stdin
from itertools import combinations
from copy import deepcopy

N,M,D = map(int,stdin.readline().rstrip().split())
enemy = [stdin.readline().rstrip().split() for _ in range(N)]
enemy = enemy[::-1]
answer = 0

for case in list(combinations([i for i in range(M)],3)):
    eliminated = 0
    tmp = deepcopy(enemy)
    nn = N
    while tmp:
        attacked = set()
        for archer in case:
            shot = False
            for d in range(1,D+1):
                i,j = -1,archer-d
                while j < archer+d:
                    if j < archer : i += 1
                    else : i -= 1
                    j += 1
                    if i<0 or i >=nn or j<0 or j>=M : continue
                    else : 
                        if tmp[i][j]=='1' :
                            shot = True
                            break
                if shot : 
                    attacked.add((i,j))
                    break
        for shoted in attacked:
            tmp[shoted[0]][shoted[1]] = '0'
            eliminated += 1
        tmp.pop(0)
        nn -= 1
    answer = max(answer,eliminated)
print(answer)