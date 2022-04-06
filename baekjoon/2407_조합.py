from sys import stdin

def fact(n):
    if n==0 : return 1
    else : return n*fact(n-1)

n,m = map(int,stdin.readline().rstrip().split())

print(int(fact(n)//(fact(m)*fact(n-m))))