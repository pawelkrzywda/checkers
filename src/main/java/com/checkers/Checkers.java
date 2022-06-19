package com.checkers;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Checkers extends Application implements MouseListener {

    /*EventHandler<ActionEvent> mouseEventHandler(){
        return event -> {
            Node node = (Node) event.getTarget();
            int row = GridPane.getRowIndex(node);
            int column = GridPane.getColumnIndex(node);
            System.out.println("Column: " + column + " , row: " + row);
        };
    }

    Node getNodeByCoordinate(GridPane grid, Integer row, Integer column) {
        for (Node node : grid.getChildren()) {
            if(GridPane.getColumnIndex(node) == row && GridPane.getColumnIndex(node) == column){
                return node;
            }
        }
        return null;
    }*/

    private Image imageback = new Image("file:src/main/resources/board.png");

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click!");
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}

    public boolean game = true;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setBackground(background);

        ObservableList<Piece> piecesList = FXCollections.observableArrayList(piece -> new Observable[] {piece.getIsActive(), piece.getObsColumn(), piece.getObsRow()});
        Piece red1Piece = new Piece("red",  1,0 , false);
        Piece red2Piece = new Piece("red", 3,0 , false);
        Piece red3Piece = new Piece("red", 5,0 , false);
        Piece red4Piece = new Piece("red", 7,0 , false);
        Piece red5Piece = new Piece("red", 0,1 , false);
        Piece red6Piece = new Piece("red", 2,1 , false);
        Piece red7Piece = new Piece("red", 4,1 , false);
        Piece red8Piece = new Piece("red", 6,1 , false);
        Piece red9Piece = new Piece("red", 1,2, false);
        Piece red10Piece = new Piece("red", 3,2 , false);
        Piece red11Piece = new Piece("red", 5,2, false );
        Piece red12Piece = new Piece("red", 7,2 , false);
        piecesList.addAll(red1Piece, red2Piece, red3Piece, red4Piece, red5Piece, red6Piece, red7Piece, red8Piece,
                red9Piece, red10Piece, red11Piece, red12Piece);

        Piece blue1Piece = new Piece("blue", 0,5, false);
        Piece blue2Piece = new Piece("blue", 2,5, false);
        Piece blue3Piece = new Piece("blue", 4,5, false);
        Piece blue4Piece = new Piece("blue", 6,5, false);
        Piece blue5Piece = new Piece("blue", 1,6, false);
        Piece blue6Piece = new Piece("blue", 3,6, false);
        Piece blue7Piece = new Piece("blue", 5,6, false);
        Piece blue8Piece = new Piece("blue", 7,6, false);
        Piece blue9Piece = new Piece("blue", 0,7, false);
        Piece blue10Piece = new Piece("blue", 2,7, false);
        Piece blue11Piece = new Piece("blue", 4,7, false);
        Piece blue12Piece = new Piece("blue", 6,7, false);
        piecesList.addAll(blue1Piece, blue2Piece, blue3Piece, blue4Piece, blue5Piece, blue6Piece, blue7Piece,
                blue8Piece, blue9Piece, blue10Piece, blue11Piece, blue12Piece);

        for(int i=0;i<8;i++){
            if(i%2 == 0){
                piecesList.add(new Piece("empty", i,0, false));
                piecesList.add(new Piece("empty", i,2, false));
                piecesList.add(new Piece("empty", i,3, false));
                piecesList.add(new Piece("empty", i,4, false));
                piecesList.add(new Piece("empty", i,6, false));
            } else {
                piecesList.add(new Piece("empty", i,1, false));
                piecesList.add(new Piece("empty", i,3, false));
                piecesList.add(new Piece("empty", i,4, false));
                piecesList.add(new Piece("empty", i,5, false));
                piecesList.add(new Piece("empty", i,7, false));
            }
        }

        for(Piece piece : piecesList){
            grid.add(piece.getPiece(), piece.getColumn(), piece.getRow(),1,1);
        }

        Scene scene = new Scene(grid, 800, 800, Color.BLACK);
        grid.setMaxSize(800,800);
        grid.setMinSize(800, 800);

        for(int i=0; i<8; i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(12.5);
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 8; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(12.5);
            grid.getRowConstraints().add(row);
        }


        piecesList.addListener(new ListChangeListener<Piece>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Piece> c) {
                while (c.next()) {
                    if (c.wasUpdated()) {
                        int changeType = 0;
                        //1-button pressed, 2-button unpressed, 3-move

                        changeType = areAllInactive(piecesList);
                        Piece currentPiece = null;
                        List<Piece> moves = new LinkedList<>();
                        for (Piece piece : piecesList) {
                            if(piece.getIsActive().getValue()==true){
                                currentPiece = piece;
                            }
                        }
                        if(currentPiece!=null){
                            moves = findMoves(currentPiece, piecesList);
                        }
                        if(changeType==1 && moves.size()==0){
                            changeType=3;
                        }

                        if (changeType == 1) {
                            //disable other pieces
                            for (Piece piece : piecesList) {
                                if (piece.getIsActive().getValue() == false && piece.getColor()=="blue") {
                                    piece.disablePiece();
                                }
                            }
                            //show possible moves
                            if(currentPiece != null) {
                                for(Piece move : moves) {
                                    for (Piece piece : piecesList) {
                                        if(move.equals(piece)){
                                            piece.enablePiece();
                                        }
                                    }
                                }
                            }
                        }

                        if (changeType == 2) {
                            System.out.println("Disabling pieces etc.");
                            //enable blue pieces for different move
                            for (Piece piece : piecesList) {
                                if (piece.getIsActive().getValue() == false && piece.getColor() == "blue") {
                                    piece.enablePiece();
                                }
                                if(piece.getIsActive().getValue()==false && piece.getColor()=="empty"){
                                    piece.disablePiece();
                                }
                            }
                        }

                        if(changeType==3){
                            System.out.println("no more possible moves");
                            System.out.println("current piece column:" + currentPiece.getColumn());
                            int tempcolumn=currentPiece.getColumn();
                            int temprow=currentPiece.getRow();
                            for(Piece piece : piecesList){
                                if(piece.getIsActive().getValue()==true && piece.getColor()=="blue"){
                                    currentPiece.setColumn(piece.getColumn());
                                    currentPiece.setRow(piece.getRow());
                                    piece.setColumn(tempcolumn);
                                    piece.setRow(temprow);
                                    currentPiece.disablePiece();
                                }
                            }
                            for(Piece piece : piecesList){
                                piece.setIsActive(false);
                                if(piece.getColor()=="empty"){
                                    piece.disablePiece();
                                }
                            }

                        }



                    }
                }
            }

        });

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public int areAllInactive(ObservableList<Piece> list){
        int blueCounter=0;
        for (Piece piece : list) {
            if (piece.getIsActive().getValue()==true && piece.getColor()=="blue"){
                blueCounter=1;
            }
            }
        if(blueCounter==0){
            return 2;
        }else{return 1;}


    }

    public List findMoves(Piece currentPiece, ObservableList<Piece> list){
        List<Piece> moves = new LinkedList<Piece>();
        int column = currentPiece.getColumn();
        int row = currentPiece.getRow();

        //closest moves
        for(Piece piece : list){
            //closest moves
            if(currentPiece.getColor()!="empty") {
                if (piece.getColor() == "empty") {
                    if (piece.getColumn() == column - 1 && piece.getRow() == row - 1) {
                        moves.add(piece);
                    } else if (piece.getColumn() == column + 1 && piece.getRow() == row - 1) {
                        moves.add(piece);
                    } else if (piece.getColumn() == column + 1 && piece.getRow() == row + 1) {
                        if (piece.isKing()) {
                            moves.add(piece);
                        }
                    } else if (piece.getColumn() == column - 1 && piece.getRow() == row + 1) {
                        if (piece.isKing()) {
                            moves.add(piece);
                        }
                    }
                }
            }
            //single captures
            if(piece.getColor() == "red") {
                if (piece.getColumn() == column-1 && piece.getRow() == row-1) {
                    for(Piece emptyPiece : list){
                        if (emptyPiece.getColumn() == column-2 && emptyPiece.getRow() == row-2 && emptyPiece.getColor()=="empty"){
                            moves.add(emptyPiece);
                        }
                    }
                } else if (piece.getColumn() == column+1 && piece.getRow() == row-1){
                    for(Piece emptyPiece : list){
                        if (emptyPiece.getColumn() == column+2 && emptyPiece.getRow() == row-2 && emptyPiece.getColor()=="empty"){
                            moves.add(emptyPiece);
                        }
                    }
                }else if(piece.getColumn() == column+1 && piece.getRow() == row+1){
                    for(Piece emptyPiece : list){
                        if (emptyPiece.getColumn() == column+2 && emptyPiece.getRow() == row+2 && emptyPiece.getColor()=="empty"){
                            moves.add(emptyPiece);
                        }
                    }
                } else if(piece.getColumn() == column-1 && piece.getRow() == row+1){
                    for(Piece emptyPiece : list){
                        if (emptyPiece.getColumn() == column-2 && emptyPiece.getRow() == row+2 && emptyPiece.getColor()=="empty"){
                            moves.add(emptyPiece);
                        }
                    }
                }
            }
        }
        return moves;
    }
}

