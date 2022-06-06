package com.checkers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Piece {
    private String color;
    private int column;
    private int row;
    private boolean isOnBoard=true;

    private Image redpiece = new Image("file:src/main/resources/red.png");
    private Image bluepiece = new Image("file:src/main/resources/blue.png");
    ImageView red = new ImageView(redpiece);
    ImageView blue = new ImageView(bluepiece);

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

    public ImageView getPiece() {
        if(color == "red"){
            return red;
        }else {
            return blue;
        }
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isOnBoard() {
        return isOnBoard;
    }

    public void pieceKnocked(){
        isOnBoard = false;
    }
}
