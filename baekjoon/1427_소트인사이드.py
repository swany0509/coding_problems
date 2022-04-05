from sys import stdin
N = list(stdin.readline().strip())
print("".join(sorted(N,reverse=True)))