from sys import stdin
S,T,D = '1','2','3'
res=[]
def hanoi(n,s,d,t):
    if n==0 : return
    hanoi(n-1,s,t,d)
    res.append(s+' '+d)
    hanoi(n-1,t,d,s)

N = int(stdin.readline().rstrip())
hanoi(N,S,D,T)
print(len(res))
print('\n'.join(res))