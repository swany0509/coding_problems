from sys import stdin
al = ['zero','one','two','three','four','five','six','seven','eight','nine']
M,N = map(int,stdin.readline().rstrip().split())
dic = { (((al[i//10]+' ') if i>9 else '')+al[i%10]):i for i in range(M,N+1)}
li = sorted(dic.keys())
for i in range(N-M+1):
    print(dic[li[i]],end=' ')
    if i%10==9 : print()