from sys import stdin

N,M = map(int,stdin.readline().strip().split())
pokenum = {}
pokelis = {}
for i in range(1,N+1):
    s = stdin.readline().strip()
    pokenum[str(i)]=s
    pokelis[s]=str(i)

for i in range(M):
    s = stdin.readline().strip()
    if s.isalpha() : print(pokelis[s])
    else : print(pokenum[s])