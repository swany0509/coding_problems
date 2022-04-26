from sys import stdin

while(True):
    A,B,C = map(int,stdin.readline().split())
    if A+B+C==0 : break
    aa,bb,cc = A*A,B*B,C*C

    if 2*max(aa,bb,cc) == aa+bb+cc : print("right")
    else : print("wrong")