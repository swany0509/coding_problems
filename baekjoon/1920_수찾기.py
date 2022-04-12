from sys import stdin

dic = {}
N = stdin.readline().strip()
for s in (list(map(int,stdin.readline().strip().split()))): 
    dic[s]=1
M = stdin.readline().strip()
for s in (list(map(int,stdin.readline().strip().split()))): 
    if s not in dic.keys() : print(0)
    else : print(1)