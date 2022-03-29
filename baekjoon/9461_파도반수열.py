from sys import stdin
lis = [0,1,1,1,2,2,3,4,5]
for _ in range(100): lis.append(lis[-1]+lis[-5])
for __ in range(int(stdin.readline().strip())) : print(lis[int(stdin.readline().strip())])