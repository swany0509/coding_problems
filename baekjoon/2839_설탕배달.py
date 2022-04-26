from sys import stdin

N = int(stdin.readline())

ans = 999999
if N%5==0 : 
    ans = N/5
    print(int(ans))
else :
    if N%3==0 : ans = N/3
    num=N
    cnt=0
    while num>0 :
        num-=3
        cnt+=1
        if num%5==0:
            ans = min(ans,cnt+num/5)
            break
        elif num <0:
            break
    
    if ans == 999999 : print(-1)
    else : print(int(ans))
    