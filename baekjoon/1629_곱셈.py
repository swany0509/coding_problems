from sys import stdin

a,b,c = map(int,stdin.readline().strip().split())

def func(a,b):
    if b==1 : return a%c
    else:
        tmp = func(a,b//2)
        if b%2==0 : return (tmp**2)%c
        else : return ((tmp**2)*a)%c

print(func(a,b))