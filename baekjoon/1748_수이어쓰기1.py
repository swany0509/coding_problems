from sys import stdin

N = int(stdin.readline())
res = 0
cnt=1
numnine = 9
mulnine = 9

while True:
    if N>numnine:
        res+=cnt*mulnine
        cnt+=1
        numnine = numnine*10 + 9
        mulnine*=10
    else:
        res+=(N-10**(cnt-1)+1)*cnt
        break

print(res)