package com.tss.model;

import java.util.Scanner;

public class MoveStratergy implements IMoveStreatergy {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int[] getNextMove(char[][] board) {
        System.out.print("Enter row (0-2): ");
        int row = scanner.nextInt();

        System.out.print("Enter column (0-2): ");
        int col = scanner.nextInt();

        return new int[]{row, col};  // Let Board.validate() handle correctness
    }
}
