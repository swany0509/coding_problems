from sys import stdin
fib1,fib2,res = 0,0,1
for _ in range(int(stdin.readline().strip())) : fib1,fib2,res = fib2,res,(fib2+res)%15746
print(res)