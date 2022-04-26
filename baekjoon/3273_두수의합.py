from sys import stdin

N = int(stdin.readline().strip())
data = list(map(int,stdin.readline().split()))
target = int(stdin.readline().strip())

data.sort()
cnt,i,j = 0,0,N-1

while(i<j):
    num = data[i]+data[j]
    if num == target :
        cnt+=1 ; i+=1 ; j-=1 
    elif num > target : j-=1
    else : i +=1

print(cnt)