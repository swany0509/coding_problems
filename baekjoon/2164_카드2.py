from sys import stdin

N = int(stdin.readline().strip())
queue = [i+1 for i in range(N)]

while(len(queue)>2):
    if len(queue)%2==0:
        queue = queue[1::2]
    else:
        queue = [queue[-1]]+queue[1::2]

print(queue[-1])