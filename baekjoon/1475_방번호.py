from sys import stdin
from math import ceil
N = stdin.readline().rstrip()
dic = {str(i):0 for i in range(0,10)}
cnt = 0
for s in N:
    if s=='6' or s=='9' : cnt+=1
    else : dic[s]+=1
print(max(max(dic.values()),ceil(cnt/2)))