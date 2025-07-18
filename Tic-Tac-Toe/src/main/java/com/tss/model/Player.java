package com.tss.model;

public class Player {
    private final String name;
    private final char symbol;
    private final IMoveStreatergy moveStrategy;

    public Player(String name, char symbol, IMoveStreatergy moveStrategy) {
        this.name = name;
        this.symbol = symbol;
        this.moveStrategy = moveStrategy;
    }

    public void makeMove(Board board) {
        boolean moveMade = false;
        while (!moveMade) {
            int[] move = moveStrategy.getNextMove(board.getBoard());
            moveMade = board.placeMark(move[0], move[1], symbol);
            if (!moveMade) {
                System.out.println("Invalid move. Try again.");
            }
        }
    
}
    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}
