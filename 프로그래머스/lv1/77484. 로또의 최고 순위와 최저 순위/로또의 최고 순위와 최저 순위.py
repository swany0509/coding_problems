def solution(lottos, win_nums):
    zero_cnt=0
    cnt=0
    for i in lottos:
        if i==0 : zero_cnt+=1
        elif i in win_nums : cnt+=1
    return [6 if cnt+zero_cnt==0 else 7-(cnt+zero_cnt),6 if cnt==0 else 7-cnt]