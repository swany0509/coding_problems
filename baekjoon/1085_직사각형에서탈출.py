from sys import stdin
x,y,w,h = map(int,stdin.readline().split())
print(min(x,y,w-x,h-y))