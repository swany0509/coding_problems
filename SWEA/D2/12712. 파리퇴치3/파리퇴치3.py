import math

T = int(input())

# plus x,y
px = [-1,0,1,0]
py = [0,1,0,-1]
# multiple x,y
mx = [-1,1,1,-1]
my = [1,1,-1,-1]


for t in range(T):
  nms = input().split()
  N = int(nms[0])
  M = int(nms[1])

  data = [[int(i) for i in input().split()] for j in range(N)]

  maxData = 0

  # '+' 방향
  for i in range(N):
    for j in range(N):
      temp = data[i][j]
      ### 로직
      for m in range(1,M):
        for k in range(4):
          ii = i+px[k]*m
          jj = j+py[k]*m
          if ii >=0 and ii < N and jj >= 0 and jj < N :
            temp += data[ii][jj]

      maxData = max(maxData,temp)

  # 'x' 방향 
  for i in range(N):
    for j in range(N):
      temp = data[i][j]
      ### 로직
      for m in range(1,M):
        for k in range(4):
          ii = i+mx[k]*m
          jj = j+my[k]*m
          if ii >=0 and ii < N and jj >= 0 and jj < N :
            temp += data[ii][jj]

      maxData = max(maxData,temp)
  print("#%d %d" % ((t+1),maxData))
  
