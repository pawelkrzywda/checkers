package com.checkers;

public class Piece {
    private String color;
    private int column;
    private int row;
    boolean isOnBoard=true;

    public Piece(String color, int column, int row) {
        this.color = color;
        this.column = column;
        this.row = row;
    }

    public String getColor() {
        return color;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isOnBoard() {
        return isOnBoard;
    }

    public void pieceKnocked(){
        isOnBoard = false;
    }
}
