from sys import stdin
N = int(stdin.readline().rstrip())
data = [stdin.readline().rstrip() for _ in range(N)]
for i in range(len(data[0])-1,-1,-1):
    tmp = len(set([ss[i:] for ss in data]))
    if tmp==N :
        print(len(data[0])-i)
        break