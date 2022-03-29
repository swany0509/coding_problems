from sys import stdin

E, S, M = map(int,stdin.readline().split())
data = {15:E, 28:S, 19:M}

while True:
    mi = min(data.values())
    ma = max(data.values())

    if mi==ma: break

    for key, value in data.items():
        if value==mi:
            data[key]+=key

print(mi)