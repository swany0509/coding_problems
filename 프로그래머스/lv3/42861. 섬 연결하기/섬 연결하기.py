import sys

parents = [i for i in range(101)]

def find(n):
    if n == parents[n]:
        return parents[n]
    else:
        parents[n] = find(parents[n])
        return parents[n]

def union(a, b):
    aroot = find(a)
    broot = find(b)
    if (aroot == broot):
        return 0
    else:
        if a < b : parents[aroot] = broot
        else : parents[broot] = aroot
        return 1

def solution(n, costs):
    answer = 0
    count = 0
    for a, b, c in sorted(costs,key=lambda x:x[2]):
        if union(a, b) == 1:
            answer += c
            count += 1
            if count == n-1 : break
    return answer

