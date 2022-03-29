from sys import stdin

N = int(stdin.readline())
target = list(map(int,stdin.readline().split()))

if N==1 : print(-1)
else:
    idx=N
    for i in range(N-2,-1,-1):
        if target[i] > target[i+1] :
            idx = i
            break
    if idx==N : print(-1)
    else:
        idx2=N
        for i in range(N-1,-1,-1):
            if target[idx] > target[i]:
                target[idx],target[i] = target[i], target[idx]
                break
        target = target[:idx+1]+sorted(target[idx+1:],reverse=True)
        print(*target)
