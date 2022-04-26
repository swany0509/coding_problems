from sys import stdin
from math import lcm

weekday = ['Saturday', 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday']

fsh,fsm = map(int,stdin.readline().rstrip().split(':'))
ssh,ssm = map(int,stdin.readline().rstrip().split(':'))

fh,fm = map(int,stdin.readline().rstrip().split(':'))
sh,sm = map(int,stdin.readline().rstrip().split(':'))

f_start_time = fsh*60+fsm
s_start_time = ssh*60+ssm


f_duration = fh*60+fm
s_duration = sh*60+sm

llcm = lcm(f_duration,s_duration)
cnt=0
for i in range(llcm):
    cnt+=1
    if f_start_time == s_start_time :
        break
    if f_start_time < s_start_time : f_start_time += f_duration
    else : s_start_time += s_duration

if cnt==llcm :
    print('Never')
else:
    print(weekday[(f_start_time//1440)%7])
    f_start_time %= 1440
    hh = str(f_start_time//60)
    mm = str(f_start_time%60)
    print('0'*(2-len(hh)) + hh+':'+'0'*(2-len(mm)) +mm)