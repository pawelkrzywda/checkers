package com.checkers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Piece {
    private String color;
    private int column;
    private int row;
    private boolean isOnBoard=true;
    private BooleanProperty isActive = new SimpleBooleanProperty();

    private Button button = new Button();

    private Image redpiece = new Image("file:src/main/resources/red.png");
    private Image bluepiece = new Image("file:src/main/resources/blue.png");
    ImageView red = new ImageView(redpiece);
    ImageView blue = new ImageView(bluepiece);

    public Piece(String color, int column, int row, boolean isActive) {
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
        } else{
            button.setStyle("-fx-background-color: green;");
            button.setMaxSize(90, 90);
            button.setMinSize(90,90);
            button.setDisable(true);
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

    public BooleanProperty getIsActive() {
        return  isActive;
    }

    public void disablePiece(){
        if(isActive.getValue() == false){
            button.setDisable(true);
        }
    }

    public void enablePiece(){
        button.setDisable(false);
    }
}
