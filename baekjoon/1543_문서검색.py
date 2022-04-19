from sys import stdin
raw = stdin.readline().rstrip()
data = stdin.readline().rstrip()
print(raw.replace(data,'*').count('*'))