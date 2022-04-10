from sys import stdin

count = [0,0]
prev = 2
for s in stdin.readline().rstrip():
    if s != prev : count[int(s)]+=1
    prev = s

print(min(count))