from sys import stdin
zi = [0 for _ in range(21)]
for _ in range(int(stdin.readline().strip())):
    comm = stdin.readline().strip().split()
    if comm[0] == 'add' : zi[int(comm[1])] = 1
    elif comm[0] == 'remove' : zi[int(comm[1])] = 0
    elif comm[0] == 'check' : print(zi[int(comm[1])])
    elif comm[0] == 'toggle' : zi[int(comm[1])] = abs(1-zi[int(comm[1])])
    elif comm[0] == 'all' : zi = [1 for i in range(21)]
    else : zi = [0 for i in range(21)]