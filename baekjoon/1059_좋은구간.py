from sys import stdin

L = int(stdin.readline().strip())
data = sorted(list(map(int,stdin.readline().strip().split())))
N = int(stdin.readline().strip())

left,right = 0,0

if L==1 or N < data[0] :
    if N==data[0] : print(0)
    elif N==1 :
        if data[0]==1 or data[0]==2 : print(0)
        else : print(data[0]-2)
    else :
        lnums = N-1
        rnums = data[0]-N-1
        print(lnums + rnums + lnums*rnums)
else :
    for num in data:
        if left != 0 :
            if num > N :
                right = num 
                break
            else : left = num
        else : left = num
    if right==0 : right==1000

    if left == N or right == N : print(0)
    else :
        lnums = N-left-1
        rnums = right-N-1
        print(lnums + rnums + lnums*rnums)