from sys import stdin

data = list(map(int,stdin.readline().split()))
aa = set(data)

if len(aa) == 1: print(10000+(data[0]*1000))
elif len(aa) == 2 : print(1000+(sorted(data)[1]*100))
else : print(max(data)*100)