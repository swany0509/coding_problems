from sys import stdin

N,a,b = map(int,stdin.readline().rstrip().split())
data = [1 for _ in range(1,N+1)]
data[a-1] = 0
data[b-1] = 0
level = 1
end = False

while True:
    tmp = []
    N = len(data)
    for i in range(0,N,2):
        if N%2==1 and i==N-1 : tmp.append(data[-1])
        elif data[i] == 0 :
            if data[i+1]== 0 :
                end = True
                break
            else : tmp.append(0)
        elif data[i+1] == 0 : tmp.append(0)
        else : tmp.append(1)
    if end : break
    data = tmp[:]
    level += 1

print(level)
