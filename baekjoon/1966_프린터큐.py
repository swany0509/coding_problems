from sys import stdin

for _ in range(int(stdin.readline().strip())):
    N,M = map(int,stdin.readline().strip().split())
    raw = list(map(int,stdin.readline().strip().split()))
    num = [ i for i in range(N) ]
    cnt=0
    if len(raw)==1 : print(1)
    else:
        while True:
            if len(raw)==1:
                print(cnt+1)
                break
            if raw[0] < max(raw[1:]) :
                raw = raw[1:] + [raw[0]]
                num = num[1:] + [num[0]]
            else :
                if num[0] == M :
                    print(cnt+1)
                    break
                else :
                    cnt+=1
                    raw.pop(0)
                    num.pop(0)
