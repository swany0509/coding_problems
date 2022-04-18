from sys import stdin

for _ in range(int(stdin.readline().rstrip())):
    G = int(stdin.readline().rstrip())
    students = [int(stdin.readline().rstrip()) for __ in range(G)]
    M=1
    while M<1000000:
        tmp = set([ss%M for ss in students])
        if len(tmp)==G : break
        M+=1
    print(M)