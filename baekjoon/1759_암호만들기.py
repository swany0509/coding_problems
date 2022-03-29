from sys import stdin
from itertools import combinations

N,A = map(int,stdin.readline().split())
alpha = set(stdin.readline().split())
vowel = set(['a','e','i','o','u']) & alpha
conso = sorted(alpha - vowel)
vowel = sorted(vowel)

ans=[]
for i in range(1,N-1):
    for vv in list(combinations(vowel,i)):
        for cc in list(combinations(conso,N-i)):
            ans.append("".join(sorted(list(vv)+list(cc))))

for i in sorted(ans): print(i)