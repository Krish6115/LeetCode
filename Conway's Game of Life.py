import time
import os
import random
rows = 20
columns = 40
def create_grid():
    return [[random.choice([0, 1]) for _ in range(columns)] for _ in range(rows)]
def print_grid(grid):
    os.system('cls' if os.name == 'nt' else 'clear')
    for row in grid:
        line = ""
        for cell in row:
            if cell == 1:
                line += "#"
            else:
                line += "."
        print(line)
def count_neighbors(grid, row_index, column_index):
    total = 0
    for i in range(row_index - 1, row_index + 2):
        for j in range(column_index - 1, column_index + 2):
            if i == row_index and j == column_index:
                continue
            if 0 <= i < rows and 0 <= j < columns:
                total += grid[i][j]
    return total
def next_generation(grid):
    new_grid = [[0 for _ in range(columns)] for _ in range(rows)]
    for i in range(rows):
        for j in range(columns):
            neighbors = count_neighbors(grid, i, j)
            if grid[i][j] == 1:
                if neighbors == 2 or neighbors == 3:
                    new_grid[i][j] = 1
            else:
                if neighbors == 3:
                    new_grid[i][j] = 1
    return new_grid

grid = create_grid()

while True:
    print_grid(grid)
    grid = next_generation(grid)
    time.sleep(0.2)
