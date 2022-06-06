package com.checkers;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Checkers extends Application {
    private Image imageback = new Image("file:src/main/resources/board.png");

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
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setBackground(background);

        ObservableList<Piece> piecesList = FXCollections.observableArrayList();
        Piece red1Piece = new Piece("red", 0,0 );
        Piece red2Piece = new Piece("red", 2,0 );
        Piece red3Piece = new Piece("red", 4,0 );
        Piece red4Piece = new Piece("red", 6,0 );
        Piece red5Piece = new Piece("red", 1,1 );
        Piece red6Piece = new Piece("red", 3,1 );
        Piece red7Piece = new Piece("red", 5,1 );
        Piece red8Piece = new Piece("red", 7,1 );
        Piece red9Piece = new Piece("red", 0,2 );
        Piece red10Piece = new Piece("red", 2,2 );
        Piece red11Piece = new Piece("red", 4,2 );
        Piece red12Piece = new Piece("red", 6,2 );
        piecesList.addAll(red1Piece, red2Piece, red3Piece, red4Piece, red5Piece, red6Piece, red7Piece, red8Piece,
                red9Piece, red10Piece, red11Piece, red12Piece);

        Piece blue1Piece = new Piece("blue", 0,5);
        Piece blue2Piece = new Piece("blue", 2,5);
        Piece blue3Piece = new Piece("blue", 4,5);
        Piece blue4Piece = new Piece("blue", 6,5);
        Piece blue5Piece = new Piece("blue", 1,6);
        Piece blue6Piece = new Piece("blue", 3,6);
        Piece blue7Piece = new Piece("blue", 5,6);
        Piece blue8Piece = new Piece("blue", 7,6);
        Piece blue9Piece = new Piece("blue", 0,7);
        Piece blue10Piece = new Piece("blue", 2,7);
        Piece blue11Piece = new Piece("blue", 4,7);
        Piece blue12Piece = new Piece("blue", 6,7);
        piecesList.addAll(blue1Piece, blue2Piece, blue3Piece, blue4Piece, blue5Piece, blue6Piece, blue7Piece,
                blue8Piece, blue9Piece, blue10Piece, blue11Piece, blue12Piece);

        for(Piece piece : piecesList){
            grid.add(piece.getPiece(), piece.getColumn(), piece.getRow(),1,1);
        }

        Scene scene = new Scene(grid, 800, 800, Color.BLACK);

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

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}