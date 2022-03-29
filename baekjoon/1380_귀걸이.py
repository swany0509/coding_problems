from sys import stdin

missed = []
tmp = []
dic = {}

N = stdin.readline().strip().split()

while(True):
    if len(N) == 1:
        if N[0] == '0' : 
            missed.extend(tmp)
            break
        else:
            missed.extend(tmp)
            tmp.clear()
            dic.clear()
            for i in range(1,int(N[0])+1):
                dic[i] = stdin.readline().strip()
    else:
        t = dic[int(N[0])]
        if t not in tmp : tmp.append(t)
        else : tmp.remove(t)
    N = stdin.readline().strip().split()

print('\n'.join([str(i+1)+" "+missed[i] for i in range(len(missed))]))