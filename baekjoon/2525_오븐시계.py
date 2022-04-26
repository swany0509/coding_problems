from sys import stdin

hh,mm = map(int,stdin.readline().split())
spend = int(stdin.readline())

sh,sm = spend//60,spend%60
if mm+sm >=60 : 
    mm = mm+sm-60
    hh+=1
else : mm+=sm

print((hh+sh)%24,mm)
