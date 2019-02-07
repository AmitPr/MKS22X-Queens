public class QueenBoard {
	// Temp Driver method
	public static void main(String[] args) {
		QueenBoard qb = new QueenBoard(8);
		qb.solve();
		System.out.println(qb.toString() + "\n");
	}

	private int[][] board;
	private int size = 0;

	/*
	 * Constructor
	 */
	public Main(int size) {
		this.size = size;
		board = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = 0;
			}
		}
	}

	/*
	 * Adds a queen to the board if it won't be threatened.
	 * 
	 * @return whether or not the queen was added.
	 */
	public boolean addQueen(int r, int c) {
		if (board[c][r] == 0) {
			board[c][r] = -1;
			for (int i = 0; i < size; i++) {
				if (board[c][i] != -1) {
					board[c][i] += 1;
				}
			}
			for (int i = 0; i < size; i++) {
				if (board[i][r] != -1) {
					board[i][r] += 1;
				}
			}
			int offset = c - r;
			for (int i = 0; i < size; i++) {
				int row = i + (offset < 0 ? offset * -1 : 0);
				int col = i + (offset < 0 ? 0 : offset);
				int row2 = (r + c - i);
				if (row < size && col < size) {
					if (board[col][row] != -1) {
						board[col][row] += 1;
					}
				}
				if (row2 < size && row2 > 0) {
					if (board[i][row2] != -1) {
						board[i][row2] += 1;
					}
				}
			}
			return true;
		}
		return false;
	}

	/*
	 * Removes a queen if there is one at the location specified.
	 */
	public void removeQueen(int r, int c) {
		if (board[r][c] == -1) {
			for (int i = 0; i < size; i++) {
				if (board[c][i] != -1) {
					board[c][i] -= 1;
				}
			}
			for (int i = 0; i < size; i++) {
				if (board[i][r] != -1) {
					board[i][r] -= 1;
				}
			}
			int offset = c - r;
			for (int i = 0; i < size; i++) {
				int row = i + (offset < 0 ? offset * -1 : 0);
				int col = i + (offset < 0 ? 0 : offset);
				int row2 = (r + c - i);
				if (row < size && col < size) {
					if (board[col][row] != -1) {
						board[col][row] -= 1;
					}
				}
				if (row2 < size && row2 > 0) {
					if (board[i][row2] != -1) {
						board[i][row2] -= 1;
					}
				}
			}
			board[c][r] = 0;
		}
	}

	/**
	 * @return false when the board is not solveable and leaves the board filled
	 *         with zeros;
	 * 
	 *         true when the board is solveable, and leaves the board in a
	 *         solved state
	 * 
	 * @throws IllegalStateException
	 *             when the board starts with any non-zero value
	 * 
	 */
	public boolean solve() {
		return helper(0, 0, false);
	}

	private boolean helper(int curCol, int numQ, boolean allSolutions) {
		if (curCol < size) {
			for (int i = 0; i < size; i++) {
				if (addQueen(i, curCol)) {
					if (helper(curCol + 1, numQ + 1, allSolutions)) {
						return true;
					} else {
						removeQueen(i, curCol);
					}
				} else {
					continue;
				}
			}
			return false;
		}
		if (numQ == size) {
			return true;
		}
		return false;
	}

	/**
	 * @return The output string formatted as follows: All numbers that
	 *         represent queens are replaced with 'Q' all others are displayed
	 *         as underscores '_' There are spaces between each symbol: """_ _ Q
	 *         _ Q _ _ _
	 * 
	 *         _ _ _ Q
	 * 
	 *         _ Q _ _""" (pythonic string notation for clarity, excludes the
	 *         character up to the *)
	 */
	public String toString() {
		String b = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == -1) {
					b = b + "Q ";
				} else {
					/*
					 * if (board[i][j] > 0) { b = b + board[i][j] + " "; } else
					 * {
					 */
					b = b + "_ ";
					// }
				}
			}
			b += "\n";
		}
		return b;
	}
}
