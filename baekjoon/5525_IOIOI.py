from sys import stdin

N = int(stdin.readline().strip())
M = int(stdin.readline().strip())
S = stdin.readline().strip()

cnt,ans,i = 0,0,0
while i < M-2 :
    if S[i]=='I' and S[i+1]=='O' and S[i+2]=='I' :
        cnt+=1
        if cnt==N:
            cnt-=1
            ans+=1
        i += 2
    else : 
        cnt=0
        i += 1

print(ans)