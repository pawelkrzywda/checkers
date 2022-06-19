package com.checkers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Piece {
    private String color;
    private IntegerProperty column = new SimpleIntegerProperty();
    private IntegerProperty row = new SimpleIntegerProperty();
    private boolean isOnBoard=true;
    private boolean king = false;
    private BooleanProperty isActive = new SimpleBooleanProperty();
    private Button button = new Button();
    private Image redpiece = new Image("file:src/main/resources/red.png");
    private Image bluepiece = new Image("file:src/main/resources/blue.png");
    ImageView red = new ImageView(redpiece);
    ImageView blue = new ImageView(bluepiece);

    public Piece(String color, int column, int row, boolean isActive) {
        this.color = color;
        this.column.setValue(column);
        this.row.setValue(row);
        this.isActive.setValue(isActive);
    }

    public String getColor() {
        return color;
    }

    public int getColumn() {
        return column.getValue();
    }
    public IntegerProperty getObsColumn(){return column;}

    public int getRow() {
        return row.getValue();
    }
    public IntegerProperty getObsRow(){return row;}

    public boolean isKing() {
        return king;
    }

    public Button getPiece() {
        if(color == "red"){
            button.setGraphic(red);
            button.setStyle("-fx-background-color: transparent;");
            //button.setDisable(true);
            /*button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(!isActive.get()) {
                        button.setStyle("-fx-background-color: #FF1493;");
                        isActive.setValue(true);

                    }else{
                        button.setStyle("-fx-background-color: transparent;");
                        isActive.setValue(false);
                    }
                }
            });*/
            return button;
        }else if(color == "blue") {
            button.setGraphic(blue);
            button.setStyle("-fx-background-color: transparent;");
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(!isActive.get()) {
                        button.setStyle("-fx-background-color: #FF1493;");
                        isActive.setValue(true);
                    }else{
                        button.setStyle("-fx-background-color: transparent;");
                        isActive.setValue(false);
                    }
                }
            });
            return button;
        } else if(color == "empty"){
            button.setMaxSize(90, 90);
            button.setMinSize(90,90);
            button.setDisable(true);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(!isActive.getValue()) {
                        button.setStyle("-fx-background-color: #FF1493;");
                        isActive.setValue(true);
                    }else{
                        button.setStyle("-fx-background-color: transparent;");
                        isActive.setValue(false);
                    }
                }
            });
            return button;
        }
        return null;
    }

    public void setColumn(int column) {
        this.column.setValue(column);
    }

    public void setRow(int row) {
        this.row.setValue(row);
    }

    public boolean isOnBoard() {
        return isOnBoard;
    }

    public void pieceKnocked(){
        isOnBoard = false;
    }

    public BooleanProperty getIsActive() {
        return  isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive.set(isActive);
    }

    public void disablePiece(){

            button.setDisable(true);
            button.setStyle("-fx-background-color: transparent;");
    }

    public void enablePiece(){
        button.setDisable(false);
        if(color == "empty"){
            button.setStyle("-fx-background-color: green;");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (column != piece.column) return false;
        if (row != piece.row) return false;
        return color.equals(piece.color);
    }

    @Override
    public int hashCode() {
        int result = color.hashCode();
        result = 31 * result + column.getValue();
        result = 31 * result + row.getValue();
        return result;
    }
}
