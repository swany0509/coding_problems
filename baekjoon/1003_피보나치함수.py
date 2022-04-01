from sys import stdin

fibo = [[1,0],[0,1]]
for i in range(2,42):
    fibo.append([fibo[i-2][0]+fibo[i-1][0],fibo[i-2][1]+fibo[i-1][1]])

for _ in range(int(stdin.readline().strip())):
    tmp = fibo[int(stdin.readline().strip())]
    print(tmp[0],tmp[1])
    