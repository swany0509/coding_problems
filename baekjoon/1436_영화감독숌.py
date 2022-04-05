from sys import stdin

data = [str(i) for i in range(1,3000000)]
stack = []

for s in data:
    if '666' in s : stack.append(s)

print(stack[int(stdin.readline())-1])