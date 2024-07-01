data = ['' for i in range(75)]

for i in range(5):
  sentence = list(input())

  for j in range(len(sentence)) :
    data[j*5+i] = sentence[j]

print("".join(data))
