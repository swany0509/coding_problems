from sys import stdin
dic = {}
for _ in range(int(stdin.readline().strip())):
    dic.clear()
    for __ in range(int(stdin.readline().strip())):
        vv, key = stdin.readline().strip().split()
        if key not in dic.keys() : dic[key] = 1
        else : dic[key] += 1
    ans = 1
    for n in dic.values():
        ans *= (n+1)
    print(ans-1)