package com.checkers;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Checkers extends Application {
    private Image imageback = new Image("file:src/main/resources/board.png");
    private Image bluepiece = new Image("file:src/main/resources/blue.png");
    private Image redpiece = new Image("file:src/main/resources/red.png");
    private FlowPane redpieces = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane bluepieces = new FlowPane(Orientation.HORIZONTAL);

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

        ImageView red1 = new ImageView(redpiece);
        ImageView red2 = new ImageView(redpiece);
        ImageView red3 = new ImageView(redpiece);
        ImageView red4 = new ImageView(redpiece);
        ImageView red5 = new ImageView(redpiece);
        ImageView red6 = new ImageView(redpiece);
        ImageView red7 = new ImageView(redpiece);
        ImageView red8 = new ImageView(redpiece);
        ImageView red9 = new ImageView(redpiece);
        ImageView red10 = new ImageView(redpiece);
        ImageView red11 = new ImageView(redpiece);
        ImageView red12 = new ImageView(redpiece);

        ImageView blue1 = new ImageView(bluepiece);
        ImageView blue2 = new ImageView(bluepiece);
        ImageView blue3 = new ImageView(bluepiece);
        ImageView blue4 = new ImageView(bluepiece);
        ImageView blue5 = new ImageView(bluepiece);
        ImageView blue6 = new ImageView(bluepiece);
        ImageView blue7 = new ImageView(bluepiece);
        ImageView blue8 = new ImageView(bluepiece);
        ImageView blue9 = new ImageView(bluepiece);
        ImageView blue10 = new ImageView(bluepiece);
        ImageView blue11 = new ImageView(bluepiece);
        ImageView blue12 = new ImageView(bluepiece);

        redpieces.getChildren().add(red1);
        redpieces.getChildren().add(red2);
        redpieces.getChildren().add(red3);
        redpieces.getChildren().add(red4);
        redpieces.getChildren().add(red5);
        redpieces.getChildren().add(red6);
        redpieces.getChildren().add(red7);
        redpieces.getChildren().add(red8);
        redpieces.getChildren().add(red9);
        redpieces.getChildren().add(red10);
        redpieces.getChildren().add(red11);
        redpieces.getChildren().add(red12);

        bluepieces.getChildren().add(blue1);
        bluepieces.getChildren().add(blue2);
        bluepieces.getChildren().add(blue3);
        bluepieces.getChildren().add(blue4);
        bluepieces.getChildren().add(blue5);
        bluepieces.getChildren().add(blue6);
        bluepieces.getChildren().add(blue7);
        bluepieces.getChildren().add(blue8);
        bluepieces.getChildren().add(blue9);
        bluepieces.getChildren().add(blue10);
        bluepieces.getChildren().add(blue11);
        bluepieces.getChildren().add(blue12);

        grid.add(red1, 0, 0, 1, 1);
        grid.add(red2, 2, 0, 1, 1);
        grid.add(red3, 4, 0, 1, 1);
        grid.add(red4, 6, 0, 1, 1);
        grid.add(red5, 1, 1, 1, 1);
        grid.add(red6, 3, 1, 1, 1);
        grid.add(red7, 5, 1, 1, 1);
        grid.add(red8, 7, 1, 1, 1);
        grid.add(red9, 0, 2, 1, 1);
        grid.add(red10, 2, 2, 1, 1);
        grid.add(red11, 4, 2, 1, 1);
        grid.add(red12, 6, 2, 1, 1);

        grid.add(blue1, 0, 5, 1, 1);
        grid.add(blue2, 2, 5, 1, 1);
        grid.add(blue3, 4, 5, 1, 1);
        grid.add(blue4, 6, 5, 1, 1);
        grid.add(blue5, 1, 6, 1, 1);
        grid.add(blue6, 3, 6, 1, 1);
        grid.add(blue7, 5, 6, 1, 1);
        grid.add(blue8, 7, 6, 1, 1);
        grid.add(blue9, 0, 7, 1, 1);
        grid.add(blue10, 2, 7, 1, 1);
        grid.add(blue11, 4, 7, 1, 1);
        grid.add(blue12, 6, 7, 1, 1);

        Scene scene = new Scene(grid, 800, 800, Color.BLACK);
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();
        ColumnConstraints column4 = new ColumnConstraints();
        ColumnConstraints column5 = new ColumnConstraints();
        ColumnConstraints column6 = new ColumnConstraints();
        ColumnConstraints column7 = new ColumnConstraints();
        ColumnConstraints column8 = new ColumnConstraints();
        column1.setPercentWidth(12.5);
        column2.setPercentWidth(12.5);
        column3.setPercentWidth(12.5);
        column4.setPercentWidth(12.5);
        column5.setPercentWidth(12.5);
        column6.setPercentWidth(12.5);
        column7.setPercentWidth(12.5);
        column8.setPercentWidth(12.5);
        grid.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        RowConstraints row6 = new RowConstraints();
        RowConstraints row7 = new RowConstraints();
        RowConstraints row8 = new RowConstraints();
        row1.setPercentHeight(12.5);
        row2.setPercentHeight(12.5);
        row3.setPercentHeight(12.5);
        row4.setPercentHeight(12.5);
        row5.setPercentHeight(12.5);
        row6.setPercentHeight(12.5);
        row7.setPercentHeight(12.5);
        row8.setPercentHeight(12.5);
        grid.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}