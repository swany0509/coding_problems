from sys import stdin

r,c = map(int,stdin.readline().split())

data=[]
for _ in range(r):
    buff = list(map(int,stdin.readline().split()))
    data.append(buff)

sums=0
for ir in range(r):
    for ic in range(c):
        # ㅣ
        if ir+3 < r : sums=max(sums,data[ir][ic] +data[ir+1][ic] +data[ir+2][ic]+data[ir+3][ic])
        # ㅡ
        if ic+3 < c : sums=max(sums,sum(data[ir][ic:ic+4]))
        if ir+2 < r and ic+1 < c :
            sums = max(sums,data[ir][ic+1] +data[ir+1][ic] +data[ir+1][ic+1]+data[ir+2][ic+1], data[ir][ic+1] +data[ir+1][ic] +data[ir+1][ic+1]+data[ir+2][ic], data[ir][ic+1] +data[ir+1][ic+1] +data[ir+2][ic]+data[ir+2][ic+1])
            sums = max(sums,data[ir][ic] +data[ir+1][ic] +data[ir+2][ic]+data[ir][ic+1])
            sums = max(sums,data[ir][ic] +data[ir+1][ic] +data[ir+1][ic+1]+data[ir+2][ic+1], data[ir][ic] +data[ir+1][ic] +data[ir+2][ic]+data[ir+1][ic+1])
            sums = max(sums,data[ir][ic] +data[ir][ic+1] +data[ir+1][ic+1]+data[ir+2][ic+1], data[ir][ic] +data[ir+1][ic] +data[ir+2][ic]+data[ir+2][ic+1])
        if ir+1 < r and ic+2 < c :
            sums = max(sums,data[ir][ic+1] +sum(data[ir+1][ic:ic+3]), data[ir][ic+1] +data[ir][ic+2] +data[ir+1][ic]+data[ir+1][ic+1], data[ir][ic+2] +sum(data[ir+1][ic:ic+3]))
            sums = max(sums,data[ir][ic] +data[ir+1][ic] +data[ir][ic+1]+data[ir][ic+2])
            sums = max(sums,data[ir][ic] +data[ir][ic+1] +data[ir+1][ic+1]+data[ir+1][ic+2], data[ir][ic] +data[ir][ic+1] +data[ir][ic+2]+data[ir+1][ic+1])
            sums = max(sums,data[ir][ic] +data[ir][ic+1] +data[ir][ic+2]+data[ir+1][ic+2], data[ir][ic] +data[ir+1][ic] +data[ir+1][ic+1]+data[ir+1][ic+2])
        # ㅁ
        if ir+1 < r and ic+1 < c :
            sums = max(sums,data[ir][ic] +data[ir+1][ic] +data[ir][ic+1]+data[ir+1][ic+1])

print(sums)