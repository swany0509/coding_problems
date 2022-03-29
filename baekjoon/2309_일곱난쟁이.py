from sys import stdin

nan = []

for _ in range(9):
    nan.append(int(stdin.readline()))

err = sum(nan)-100
nan.sort()

for i in nan:
    if err-i in nan:
        nan.remove(i)
        nan.remove(err-i)
        break

for _ in nan:
    print(_)