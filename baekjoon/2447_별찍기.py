from sys import stdin

def a_star(LEN): 
    if LEN == 1: return ['*'] 
    stars = a_star(LEN//3) 
    L = [] 
    
    for S in stars: L.append(S*3) 
    for S in stars: L.append(S+' '*(LEN//3)+S) 
    for S in stars: L.append(S*3) 
    return L 

n = int(stdin.readline().strip()) 

print('\n'.join(a_star(n)))

