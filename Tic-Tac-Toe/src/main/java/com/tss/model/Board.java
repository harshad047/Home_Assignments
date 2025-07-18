package com.tss.model;

public class Board {
	private final char[][] board;

	public Board() {
		board = new char[3][3];
		reset();
	}

	public void reset() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = ' ';
	}

	public boolean placeMark(int row, int col, char symbol) {
		if (isValidMove(row, col)) {
			board[row][col] = symbol;
			return true;
		}
		return false;
	}

	public boolean isValidMove(int row, int col) {
		return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
	}

	public boolean checkWin(char symbol) {
		for (int i = 0; i < 3; i++) {
			if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol)
					|| (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol))
				return true;
		}

		return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
				|| (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
	}

	public boolean isFull() {
		for (char[] row : board)
			for (char cell : row)
				if (cell == ' ')
					return false;
		return true;
	}

	public void printBoard() {
		System.out.println("+-----------+");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(" " + board[i][j] + (j < 2 ? " |" : ""));
			}
			System.out.println();
			if (i < 2)
				System.out.println("---+---+--- ");
		}
		System.out.println("+-----------+");

	}

	public char[][] getBoard() {
		return board;
	}
}
