from sys import stdin
from itertools import permutations

N = int(stdin.readline().strip())
data = list(map(int,stdin.readline().strip().split()))
a,b,c,d = map(int,stdin.readline().strip().split())
operands = ['+']*a + ['-']*b + ['*']*c + ['/']*d

minn = 1000000001
maxx = -1000000001

for oo in list(permutations(operands,N-1)):
    res = data[0]
    for op,nn in zip(oo,data[1:]):
        if op == '+':
            res += nn
        elif op == '-':
            res -= nn
        elif op == '*':
            res *= nn
        else :
            res = int(res/nn)
    
    minn = min(res,minn)
    maxx = max(res,maxx)

print(maxx)
print(minn)
