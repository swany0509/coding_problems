from sys import stdin

def fibo2(n):
    cur1,cur2,next = 0,0,1
    for i in range(n):
        cur1,cur2,next = cur2,next,cur1+cur2+next
    return next
for i in range(int(stdin.readline())):
    N=int(stdin.readline())
    print(fibo2(N)%10007)