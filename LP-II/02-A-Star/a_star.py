import copy

INF = 1e10


class Puzzle:

    def _init_(self, elements):
        assert len(elements) == len(
            elements[0]
        ), "Puzzle Board must have same dimensions"
        self.board = elements
        self.n_dims = len(elements)

        for i in range(self.n_dims):
            for j in range(self.n_dims):
                if self.board[i][j] == 0:
                    self.pos = (i, j)
                    break

    def display(self):
        for i in range(self.n_dims):
            print(*self.board[i], sep=" ")
            print()


def move(puzzle: Puzzle) -> list[Puzzle]:
    dims = puzzle.n_dims
    x, y = puzzle.pos[0], puzzle.pos[1]
    possible_pos = []

    if x + 1 < dims and y < dims:
        possible_pos.append((x + 1, y))
    if x < dims and y + 1 < dims:
        possible_pos.append((x, y + 1))
    if x >= 1 and y >= 0:
        possible_pos.append((x - 1, y))
    if x >= 0 and y >= 1:
        possible_pos.append((x, y - 1))

    new_states = []
    for pos in possible_pos:
        posx, posy = pos[0], pos[1]
        new_elements = copy.deepcopy(puzzle.board)
        new_elements[x][y], new_elements[posx][posy] = (
            new_elements[posx][posy],
            new_elements[x][y],
        )
        new_states.append(Puzzle(new_elements))

    return new_states


def heuristic(curr_puzzle: Puzzle, goal_puzzle: Puzzle):
    assert (
        curr_puzzle.n_dims == goal_puzzle.n_dims
    ), "Current Puzzle and goal puzzle must have same dimensions"

    count = 0
    for i in range(curr_puzzle.n_dims):
        for j in range(curr_puzzle.n_dims):
            if curr_puzzle.board[i][j] != goal_puzzle.board[i][j]:
                count += 1

    return count


def is_goal(curr_puzzle: Puzzle, goal_puzzle: Puzzle):
    assert (
        curr_puzzle.n_dims == goal_puzzle.n_dims
    ), "Current Puzzle and goal puzzle must have same dimensions"

    for i in range(curr_puzzle.n_dims):
        for j in range(curr_puzzle.n_dims):
            if curr_puzzle.board[i][j] != goal_puzzle.board[i][j]:
                return False

    return True


init_puzzle = Puzzle([[1, 2, 3], [0, 4, 6], [7, 5, 8]])
print("Initial state:")
init_puzzle.display()

goal_puzzle = Puzzle([[1, 2, 3], [4, 5, 6], [7, 8, 0]])
print("Goal state:")
goal_puzzle.display()

print("---------------------------------------")

open_set: list[Puzzle] = [init_puzzle]

g_score: dict[Puzzle, int] = {}
g_score[init_puzzle] = 0

f_score: dict[Puzzle, int] = {}
f_score[init_puzzle] = heuristic(init_puzzle, goal_puzzle)

num_steps = 1
while True:

    print(">>>Step: ", num_steps)
    min_f_score_node = open_set[0]
    min_f_score = f_score.get(min_f_score_node, INF)
    for node in open_set:
        score = f_score.get(node, INF)
        if score < min_f_score:
            min_f_score = score
            min_f_score_node = node

    print("Selected State from Frontier: \n")
    current_state = min_f_score_node
    current_state.display()
    print()
    num_steps += 1

    if is_goal(current_state, goal_puzzle):
        print("Done")
        break

    print("Possible States: \n")
    open_set.remove(current_state)

    for neighbour in move(current_state):
        cost_thru_current = g_score.get(current_state, INF) + 1
        if cost_thru_current < g_score.get(neighbour, INF):
            g_score[neighbour] = cost_thru_current
            f_score[neighbour] = cost_thru_current + heuristic(neighbour, goal_puzzle)
            if neighbour not in open_set:
                open_set.append(neighbour)
                neighbour.display()
                print("g score for this state is: ", g_score[neighbour])
                print("h score for this state is: ", heuristic(neighbour, goal_puzzle))
                print("f score for this state is: ", f_score[neighbour])
                print()

    print()
    print("----------------------------------------")
    print()
