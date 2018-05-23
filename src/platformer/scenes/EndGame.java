package platformer.scenes;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import platformer.SceneManager;

public class EndGame extends VBox{
    public EndGame(SceneManager controller, GameScene gameScene, Canvas gameCanvas) {
        super(vBox(controller, gameScene, gameCanvas));
    }

    private static VBox vBox(SceneManager controller, GameScene gameScene, Canvas gameCanvas) {
        Button exitToMenuButton, restartGameButton;

        VBox vBox = new VBox(10);
        vBox.prefWidthProperty().bind(gameCanvas.widthProperty());
        vBox.prefHeightProperty().bind(gameCanvas.heightProperty());
        vBox.setAlignment(Pos.CENTER);

        Label text = new Label("You Lose");
        text.setFont(new Font("Serif", 32));
        restartGameButton = new Button();
        DropShadow dropShadow = new DropShadow(10, Color.GRAY);
        restartGameButton.setEffect(dropShadow);
        restartGameButton.setText("New Game");
        restartGameButton.setVisible(true);

        restartGameButton.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> restartGameButton.setEffect(dropShadow));

        restartGameButton.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> restartGameButton.setEffect(null));

        restartGameButton.setOnAction(actionEvent ->
                {
                    gameScene.resetGame();
                });

        exitToMenuButton = new Button();
        exitToMenuButton.setText("Return to Menu");
        exitToMenuButton.setVisible(true);

        exitToMenuButton.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> exitToMenuButton.setEffect(dropShadow));

        exitToMenuButton.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> exitToMenuButton.setEffect(null));

        exitToMenuButton.setOnAction(actionEvent -> {
            controller.switchToMenu();
        });

        vBox.getChildren().addAll(text, restartGameButton, exitToMenuButton);
        return vBox;
    }
}
