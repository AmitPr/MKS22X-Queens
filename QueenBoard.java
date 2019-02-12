import java.util.concurrent.atomic.AtomicInteger;

public class QueenBoard {
	private int[][] board;
	private int size = 0;

	public static void main(String[] args) {
		System.out.println("Start");
		QueenBoard qb = new QueenBoard(14);
		long l = System.nanoTime();
		System.out.println(qb.countSolutions());
		System.out.println((System.nanoTime() - l)/1000000000);
		System.out.print(qb.toString());
	}

	/*
	 * Constructor
	 */
	public QueenBoard(int size) {
		this.size = size;
		board = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = 0;
			}
		}
	}

	/*
	 * Adds a queen to the board.
	 */
	public void addQueen(int r, int c) {
		for (int i = 0; i < size; i++) {
			board[c][i] += 1;
			board[i][r] += 1;
			int down=r-c+i;
			int up=r+c-i;
			if (down < size && down >= 0) {
				board[i][down] += 1;
			}
			if (up < size && up >= 0) {
				board[i][up] += 1;
			}
		}
		board[c][r] = -1;
	}

	/*
	 * Removes a queen if there is one at the location specified.
	 */
	public void removeQueen(int r, int c) {
		for (int i = 0; i < size; i++) {
			board[c][i] -= 1;
			board[i][r] -= 1;
			int down=r-c+i;
			int up=r+c-i;
			if (down < size && down >= 0) {
				board[i][down] -= 1;
			}
			if (up < size && up >= 0) {
				board[i][up] -= 1;
			}
		}
		board[c][r] = 0;
	}

	/**
	 * @return false when the board is not solveable and leaves the board filled
	 *         with zeros;
	 * 
	 *         true when the board is solveable, and leaves the board in a solved
	 *         state
	 * 
	 * @throws IllegalStateException when the board starts with any non-zero value
	 * 
	 */
	public boolean solve() {
		return helper(0, 0, false, new AtomicInteger(0));
	}

	/**
	 * @return the number of solutions found, and leaves the board filled with only
	 *         0's
	 * @throws IllegalStateException when the board starts with any non-zero value
	 */
	public int countSolutions() {
		AtomicInteger ai = new AtomicInteger(0);
		helper(0, 0, true, ai);
		return ai.get();
	}

	private boolean helper(int curCol, int numQ, boolean allSolutions, AtomicInteger ai) {
		if (curCol < size) {
			for (int i = 0; i < size; i++) {
				if (board[curCol][i] == 0) {
					addQueen(i, curCol);
					if (helper(curCol + 1, numQ + 1, allSolutions, ai)) {
						if (allSolutions) {
							ai.set(ai.get() + 1);
						} else {
							return true;
						}
					}
					removeQueen(i, curCol);
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
	 * @return The output string formatted as follows: All numbers that represent
	 *         queens are replaced with 'Q' all others are displayed as underscores
	 *         '_' There are spaces between each symbol: """_ _ Q _ Q _ _ _
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
					b = b + board[i][j] + " ";
				}
			}
			b += "\n";
		}
		return b;
	}
}
