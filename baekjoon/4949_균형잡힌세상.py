from sys import stdin

while True :
    sen = stdin.readline().rstrip()
    if sen == '.' : break
    stack = []
    ans = True
    for s in sen:
        if s=='(' or s=='[' : stack.append(s)
        elif s==')' :
            if len(stack)==0 or stack[-1] != '(':
                ans = False
                break
            else : stack.pop()
        elif s==']' :
            if len(stack)==0 or stack[-1] != '[':
                ans = False
                break
            else : stack.pop()
    print("yes") if (ans and not stack) else print("no")