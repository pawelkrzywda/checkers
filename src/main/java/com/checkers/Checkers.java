package com.checkers;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
        Piece red1Piece = new Piece("red", 1, 0, false);
        Piece red2Piece = new Piece("red", 3, 0, false);
        Piece red3Piece = new Piece("red", 5, 0, false);
        Piece red4Piece = new Piece("red", 7, 0, false);
        Piece red5Piece = new Piece("red", 0, 1, false);
        Piece red6Piece = new Piece("red", 2, 1, false);
        Piece red7Piece = new Piece("red", 4, 1, false);
        Piece red8Piece = new Piece("red", 6, 1, false);
        Piece red9Piece = new Piece("red", 1, 2, false);
        Piece red10Piece = new Piece("red", 3, 2, false);
        Piece red11Piece = new Piece("red", 5, 2, false);
        Piece red12Piece = new Piece("red", 7, 2, false);
        piecesList.addAll(red1Piece, red2Piece, red3Piece, red4Piece, red5Piece, red6Piece, red7Piece, red8Piece,
                red9Piece, red10Piece, red11Piece, red12Piece);

        Piece blue1Piece = new Piece("blue", 0, 5, false);
        Piece blue2Piece = new Piece("blue", 2, 5, false);
        Piece blue3Piece = new Piece("blue", 4, 5, false);
        Piece blue4Piece = new Piece("blue", 6, 5, false);
        Piece blue5Piece = new Piece("blue", 1, 6, false);
        Piece blue6Piece = new Piece("blue", 3, 6, false);
        Piece blue7Piece = new Piece("blue", 5, 6, false);
        Piece blue8Piece = new Piece("blue", 7, 6, false);
        Piece blue9Piece = new Piece("blue", 0, 7, false);
        Piece blue10Piece = new Piece("blue", 2, 7, false);
        Piece blue11Piece = new Piece("blue", 4, 7, false);
        Piece blue12Piece = new Piece("blue", 6, 7, false);
        piecesList.addAll(blue1Piece, blue2Piece, blue3Piece, blue4Piece, blue5Piece, blue6Piece, blue7Piece,
                blue8Piece, blue9Piece, blue10Piece, blue11Piece, blue12Piece);

        Piece empty1Piece = new Piece("empty", 0, 3, false);
        Piece empty2Piece = new Piece("empty", 1, 4, false);
        Piece empty3Piece = new Piece("empty", 2, 3, false);
        Piece empty4Piece = new Piece("empty", 3, 4, false);
        Piece empty5Piece = new Piece("empty", 4, 3, false);
        Piece empty6Piece = new Piece("empty", 5, 4, false);
        Piece empty7Piece = new Piece("empty", 6, 3, false);
        Piece empty8Piece = new Piece("empty", 7, 4, false);
        piecesList.addAll(empty1Piece, empty2Piece, empty3Piece, empty4Piece, empty5Piece, empty6Piece,
                empty7Piece, empty8Piece);

        for (Piece piece : piecesList) {
            grid.add(piece.getPiece(), piece.getColumn(), piece.getRow(), 1, 1);
        }

        Scene scene = new Scene(grid, 800, 800, Color.BLACK);
        grid.setMaxSize(800, 800);
        grid.setMinSize(800, 800);

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
                            Platform.runLater(()->computerMove(piecesList, grid));
                            return;
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
        System.out.println("red column " + redPiece.getColumn());
        System.out.println("red row" + redPiece.getRow());
        System.out.println("currentNode " + currentNode);
        System.out.println("redNode " + redNode);
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
}