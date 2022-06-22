package com.checkers;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import javafx.util.Duration;
import javafx.scene.control.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Checkers extends Application implements MouseListener {

    Node getNodeByCoordinate(GridPane grid, int column, int row) {
        for (Node node : grid.getChildren()) {
            if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    private Image imageback = new Image("file:src/main/resources/board.png");

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click!");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

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

        ObservableList<Piece> piecesList = FXCollections.observableArrayList(piece -> new Observable[]{piece.getIsActive()});
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if((j==0 && i%2!=0) || (j==2 && i%2!=0)){
                    piecesList.add(new Piece("red", i, j,false));
                }else if((j==3 || j==4) && (i+j)%2!=0){
                    piecesList.add(new Piece("empty", i, j,false));
                }else if((j==5 && i%2==0) || (j==7 && i%2==0)){
                    piecesList.add(new Piece("blue", i, j,false));
                }else if(j==1 && i%2==0){
                    piecesList.add(new Piece("red", i, j,false));
                }else if(j==6 && i%2!=0){
                    piecesList.add(new Piece("blue", i, j,false));
                }
            }
        }

        for (Piece piece : piecesList) {
            grid.add(piece.getPiece(), piece.getColumn(), piece.getRow(), 1, 1);
        }

        Scene scene = new Scene(grid, 800, 800, Color.BLACK);
        primaryStage.setTitle("Checkers");
        primaryStage.setResizable(false);

        //Start menu
        Label label1= new Label("Welcome to Checkers game. Press the button bellow to start game:");
        Button button1= new Button("Start game");
        button1.setOnAction(e -> primaryStage.setScene(scene));
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        layout1.setAlignment(Pos.CENTER);
        Scene startScene= new Scene(layout1, 800, 800);
        primaryStage.setScene(startScene);
        primaryStage.show();

        //End menu
        Label label2= new Label("Game ended. Press the button below to start a new game:");
        Button button2= new Button("Start new game");
        button2.setOnAction(e -> {
            grid.getChildren().remove(0, grid.getChildren().size());
            piecesList.remove(0, piecesList.size());
            //creating new pieces and adding them to grid and piecesList
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if((j==0 && i%2!=0) || (j==2 && i%2!=0)){
                        piecesList.add(new Piece("red", i, j,false));
                    }else if((j==3 || j==4) && (i+j)%2!=0){
                        piecesList.add(new Piece("empty", i, j,false));
                    }else if((j==5 && i%2==0) || (j==7 && i%2==0)){
                        piecesList.add(new Piece("blue", i, j,false));
                    }else if(j==1 && i%2==0){
                        piecesList.add(new Piece("red", i, j,false));
                    }else if(j==6 && i%2!=0){
                        piecesList.add(new Piece("blue", i, j,false));
                    }
                }
            }
            for (Piece piece : piecesList) {
                grid.add(piece.getPiece(), piece.getColumn(), piece.getRow(), 1, 1);
            }
            primaryStage.setScene(scene);
        });
        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(label2, button2);
        layout2.setAlignment(Pos.CENTER);
        Scene endScene= new Scene(layout2, 800, 800);

        for (int i = 0; i < 8; i++) {
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
                        Piece bluePiece = null;
                        List<Piece> moves = new LinkedList<>();
                        for (Piece piece : piecesList) {
                            if (piece.getIsActive().getValue() && piece.getColor().equals("blue")) {
                                bluePiece = piece;
                            }
                            if (piece.getIsActive().getValue() && piece.getColor().equals("empty")) {
                                currentPiece = piece;
                            }
                        }

                        if (bluePiece != null && currentPiece == null) {
                            moves = findMoves("blue", bluePiece, piecesList);
                        } else if (currentPiece != null) {
                            moves = findMoves("blue", currentPiece, piecesList);
                        }

                        if (changeType == 1 && currentPiece != null && bluePiece.getIsActive().getValue()) {
                            changeType = 3;
                        }


                        if (changeType == 1) {
                            System.out.println("Change type " + changeType);
                            //disable other pieces
                            for (Piece piece : piecesList) {
                                if (piece.getIsActive().getValue() == false && piece.getColor().equals("blue")) {
                                    piece.disablePiece();
                                }
                            }
                            //show possible moves
                            if (currentPiece == null) {
                                for (Piece move : moves) {
                                    for (Piece piece : piecesList) {
                                        if (move.equals(piece)) {
                                            piece.enablePiece();
                                        }
                                    }
                                }
                            }
                        }

                        if (changeType == 2) {
                            System.out.println("Change type " + changeType);
                            //enable blue pieces for different move
                            for (Piece piece : piecesList) {
                                if (piece.getIsActive().getValue() == false && piece.getColor().equals("blue")) {
                                    piece.enablePiece();
                                }
                                if(piece.getIsActive().getValue() && piece.getColor().equals("blue")){
                                    piece.enablePiece();
                                }
                                if (piece.getIsActive().getValue() == false && piece.getColor().equals("empty")) {
                                    piece.disablePiece();
                                }
                            }
                        }

                        if (changeType == 3) {
                            System.out.println("Change type " + changeType);
                            int tempcolumn = currentPiece.getColumn();
                            int temprow = currentPiece.getRow();
                            Node redNode = null;

                            if (Math.abs(tempcolumn - bluePiece.getColumn()) == 2 && Math.abs(temprow - bluePiece.getRow()) == 2) {
                                int redColumn = (tempcolumn + bluePiece.getColumn()) / 2;
                                int redRow = (temprow + bluePiece.getRow()) / 2;
                                redNode = getNodeByCoordinate(grid, redColumn, redRow);
                                Piece pieceToDelete = null;
                                for (Iterator<Piece> iterator = piecesList.iterator(); iterator.hasNext(); ) {
                                    Piece piece = iterator.next();
                                    if (piece.getColor().equals("red") && piece.getColumn() == redColumn && piece.getRow() == redRow) {
                                        pieceToDelete = piece;
                                    }
                                }
                                grid.getChildren().remove(redNode);
                                Piece finalPieceToDelete = pieceToDelete;
                                Piece emptyPiece = new Piece("empty", redColumn, redRow, false);
                                Platform.runLater(()->  piecesList.remove(finalPieceToDelete));
                                Platform.runLater(()->piecesList.add(emptyPiece));
                                Platform.runLater(()->grid.add(emptyPiece.getPiece(), redColumn, redRow, 1, 1));
                            }

                            Node currentNode = getNodeByCoordinate(grid, currentPiece.getColumn(), currentPiece.getRow());
                            Node blueNode = getNodeByCoordinate(grid, bluePiece.getColumn(), bluePiece.getRow());
                            GridPane.setColumnIndex(currentNode, GridPane.getColumnIndex(blueNode));
                            GridPane.setRowIndex(currentNode, GridPane.getRowIndex(blueNode));
                            currentPiece.setColumn(GridPane.getColumnIndex(blueNode));
                            currentPiece.setRow(GridPane.getRowIndex(blueNode));
                            GridPane.setColumnIndex(blueNode, tempcolumn);
                            GridPane.setRowIndex(blueNode, temprow);
                            bluePiece.setColumn(tempcolumn);
                            bluePiece.setRow(temprow);

                            for (Piece piece : piecesList) {
                                piece.setIsActive(false);
                                if (piece.getColor().equals("empty")) {
                                    piece.disablePiece();
                                }
                            }

                            final KeyFrame kf1 = new KeyFrame(Duration.seconds(0.1), e -> System.out.println("pause 0,1s"));
                            final KeyFrame kf2 = new KeyFrame(Duration.seconds(0.5), e -> {if(gameEnded(piecesList)){primaryStage.setScene(endScene);}});
                            final KeyFrame kf3 = new KeyFrame(Duration.seconds(0.1), e -> computerMove(piecesList, grid));
                            final KeyFrame kf4 = new KeyFrame(Duration.seconds(0.1), e -> {if(gameEnded(piecesList)){primaryStage.setScene(endScene);}});
                            final Timeline timeline = new Timeline(kf1, kf2, kf3, kf4);
                            Platform.runLater(timeline::play);
                            return;
                        }
                    }
                }
            }
        });
    }

    public int areAllInactive(ObservableList<Piece> list) {
        int blueCounter = 0;
        for (Piece piece : list) {
            if (piece.getIsActive().getValue() && piece.getColor().equals("blue")) {
                blueCounter++;
            }
        }
        if (blueCounter == 0) {
            return 2;
        } else if (blueCounter == 1) {
            return 1;
        }
        return 0;
    }

    public List findMoves(String color, Piece currentPiece, ObservableList<Piece> list) {
        List<Piece> moves = new LinkedList<Piece>();
        int column = currentPiece.getColumn();
        int row = currentPiece.getRow();

        for (Piece piece : list) {
            //closest moves
            if (!currentPiece.getColor().equals("empty")) {
                if (piece.getColor().equals("empty")) {
                    if (piece.getColumn() == column - 1 && piece.getRow() == row - 1) {
                        if (piece.isKing() || color.equals("blue")) {
                            moves.add(piece);
                        }
                    } else if (piece.getColumn() == column + 1 && piece.getRow() == row - 1) {
                        if (piece.isKing() || color.equals("blue")) {
                            moves.add(piece);
                        }
                    } else if (piece.getColumn() == column + 1 && piece.getRow() == row + 1) {
                        if (piece.isKing() || color.equals("red")) {
                            moves.add(piece);
                        }
                    } else if (piece.getColumn() == column - 1 && piece.getRow() == row + 1) {
                        if (piece.isKing() || color.equals("red")) {
                            moves.add(piece);
                        }
                    }
                }
            }
            //single captures
            if (piece.getColor().equals("red") && currentPiece.getColor().equals("blue") || piece.getColor().equals("blue") && currentPiece.getColor().equals("red")) {
                if (piece.getColumn() == column - 1 && piece.getRow() == row - 1) {
                    for (Piece emptyPiece : list) {
                        if (emptyPiece.getColumn() == column - 2 && emptyPiece.getRow() == row - 2 && emptyPiece.getColor().equals("empty")) {
                            moves.add(emptyPiece);
                        }
                    }
                } else if (piece.getColumn() == column + 1 && piece.getRow() == row - 1) {
                    for (Piece emptyPiece : list) {
                        if (emptyPiece.getColumn() == column + 2 && emptyPiece.getRow() == row - 2 && emptyPiece.getColor().equals("empty")) {
                            moves.add(emptyPiece);
                        }
                    }
                } else if (piece.getColumn() == column + 1 && piece.getRow() == row + 1) {
                    for (Piece emptyPiece : list) {
                        if (emptyPiece.getColumn() == column + 2 && emptyPiece.getRow() == row + 2 && emptyPiece.getColor().equals("empty")) {
                            moves.add(emptyPiece);
                        }
                    }
                } else if (piece.getColumn() == column - 1 && piece.getRow() == row + 1) {
                    for (Piece emptyPiece : list) {
                        if (emptyPiece.getColumn() == column - 2 && emptyPiece.getRow() == row + 2 && emptyPiece.getColor().equals("empty")) {
                            moves.add(emptyPiece);
                        }
                    }
                }
            }
        }
        return moves;
    }

    public void computerMove(ObservableList<Piece> list, GridPane grid) {
        int redCounter=0;
        for (Piece piece : list) {
            if(piece.getColor().equals("red")){
                redCounter++;
            }
        }
        System.out.println("Computer's move.");
        List<Piece> moves = new LinkedList<>();
        Piece currentPiece = null;
        Piece redPiece = null;
        Random random = new Random();
        Node blueNode = null;
        int tempcolumn = -1;
        int temprow = -1;

        for (Piece piece : list) {
            if (piece.getColor().equals("red")) {
                moves = findMoves("red", piece, list);
                if (moves.size() > 0) {
                    int move = random.nextInt(moves.size());
                    currentPiece = moves.get(move);
                    redPiece = piece;
                    tempcolumn = currentPiece.getColumn();
                    temprow = currentPiece.getRow();
                }
            }
        }

        if (Math.abs(tempcolumn - redPiece.getColumn()) == 2 && Math.abs(temprow - redPiece.getRow()) == 2) {
            int blueColumn = (tempcolumn + redPiece.getColumn()) / 2;
            int blueRow = (temprow + redPiece.getRow()) / 2;
            blueNode = getNodeByCoordinate(grid, blueColumn, blueRow);
            Piece pieceToDelete=null;
            for (Iterator<Piece> iterator = list.iterator(); iterator.hasNext();) {
                Piece nextPiece = iterator.next();
                if(nextPiece.getColor().equals("blue") && nextPiece.getColumn()==blueColumn && nextPiece.getRow()==blueRow){
                    pieceToDelete=nextPiece;
                }
            }

            grid.getChildren().remove(blueNode);
            Piece finalPieceToDelete = pieceToDelete;
            Piece emptyPiece = new Piece("empty", blueColumn, blueRow, false);
            Platform.runLater(()->  list.remove(finalPieceToDelete));
            Platform.runLater(()->list.add(emptyPiece));
            Platform.runLater(()->grid.add(emptyPiece.getPiece(), blueColumn, blueRow, 1, 1));

        }

        Node currentNode = getNodeByCoordinate(grid, currentPiece.getColumn(), currentPiece.getRow());
        Node redNode=getNodeByCoordinate(grid, redPiece.getColumn(), redPiece.getRow());
        GridPane.setColumnIndex(currentNode, GridPane.getColumnIndex(redNode));
        GridPane.setRowIndex(currentNode, GridPane.getRowIndex(redNode));
        currentPiece.setColumn(GridPane.getColumnIndex(redNode));
        currentPiece.setRow(GridPane.getRowIndex(redNode));
        GridPane.setColumnIndex(redNode, tempcolumn);
        GridPane.setRowIndex(redNode, temprow);
        redPiece.setColumn(tempcolumn);
        redPiece.setRow(temprow);

        for (Piece piece : list) {
            piece.setIsActive(false);
            if (piece.getColor().equals("empty")) {
                piece.disablePiece();
            }
        }
    }

    public boolean gameEnded(ObservableList<Piece> list){
        int redCounter=0;
        int blueCounter=0;
        int redMoves=0;
        int blueMoves=0;
        for (Piece piece : list) {
            if (piece.getColor().equals("red")) {
                if(findMoves("red", piece, list).size()>0){
                    redMoves++;
                }
                redCounter++;
            } else if (piece.getColor().equals("blue")){
                if(findMoves("blue", piece, list).size()>0){
                    blueMoves++;
                }
                blueCounter++;
            }
        }
        if(redCounter==0 || blueCounter==0 || redMoves==0 || blueMoves==0){
            return true;
        }else{return false;}
    }
}