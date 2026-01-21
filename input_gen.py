import random

n = 50000
arr = [random.randint(1, n) for _ in range(n)]

with open("input.txt", "w") as f:
    f.write(" ".join(map(str, arr)))
