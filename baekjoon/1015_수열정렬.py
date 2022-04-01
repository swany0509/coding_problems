from sys import stdin

N = int(stdin.readline())
data = list(map(int,stdin.readline().split()))
dic = {}

for key, num in zip([i for i in range(N)],sorted(data)):
    if num not in dic.keys():
        dic[num] = [key]
    else : dic[num].append(key)

for k in data: print(dic[k].pop(0),end=' ')