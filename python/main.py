import pprint


def solve(bo):
    """
    Solves a sudoku by using an algorithm
    :param bo: 2d sudoku board
    :return: Completed sudoku
    """

    pos = find_empty(bo)
    if pos:
        row, col = pos
    else:
        return True

    for num in range(1, 10):
        if valid(bo, (row, col), num):

            bo[row][col] = num

            if solve(bo):
                return True

            bo[pos[0]][pos[1]] = 0

    return False


def valid(bo, pos, num):
    """
    Checks if chosen value is valid
    :param bo: 2d sudoku board
    :param pos: position of value on board (row, col)
    :param num: value to validate
    :return: true if value is valid, false if not
    """

    # checks row
    for col in range(len(bo)):
        if bo[pos[0]][col] == num:
            return False

    # checks col
    for row in range(len(bo)):
        if bo[row][pos[1]] == num:
            return False

    # checks box
    pos_y = pos[0] // 3
    pos_x = pos[1] // 3

    for row in range(pos_y * 3, pos_y * 3 + 3):
        for col in range(pos_x * 3, pos_x * 3 + 3):
            if bo[row][col] == num:
                return False

    return True


def find_empty(bo):
    """
    Finds first empty value
    :param bo: 2d sudoku board
    :return: position of first empty value (row, col)
    """

    for row in range(len(bo)):
        for col in range(len(bo)):
            if bo[row][col] == 0:
                return row, col

    return False


board = [
    [7, 8, 0, 4, 0, 0, 1, 2, 0],
    [6, 0, 0, 0, 7, 5, 0, 0, 9],
    [0, 0, 0, 6, 0, 1, 0, 7, 8],
    [0, 0, 7, 0, 4, 0, 2, 6, 0],
    [0, 0, 1, 0, 5, 0, 9, 3, 0],
    [9, 0, 4, 0, 6, 0, 0, 0, 5],
    [0, 7, 0, 3, 0, 0, 0, 1, 2],
    [1, 2, 0, 0, 0, 7, 4, 0, 0],
    [0, 4, 9, 2, 0, 6, 0, 0, 7]
]

pp = pprint.PrettyPrinter(width=42, compact=True)
solve(board)
pp.pprint(board)
