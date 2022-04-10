from sys import stdin

N,M = map(int,stdin.readline().strip().split())

nn = set()
for _ in range(N) : nn.add(stdin.readline().strip())

mm = set()
for _ in range(M) : mm.add(stdin.readline().strip())

nm = sorted(list(nn & mm))
print(len(nm))
print('\n'.join(nm))