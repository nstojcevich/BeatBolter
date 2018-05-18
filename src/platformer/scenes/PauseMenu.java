package platformer.scenes;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import platformer.SceneManager;


public class PauseMenu extends VBox{

    public PauseMenu(SceneManager controller, GameScene gameScene, Canvas gameCanvas) {
        super(vBox(controller, gameScene, gameCanvas));
    }

    private static VBox vBox(SceneManager controller, GameScene gameScene, Canvas gameCanvas) {
        Button exitToMenuButton, resumeGameButton;

        VBox vBox = new VBox(10);
        vBox.prefWidthProperty().bind(gameCanvas.widthProperty());
        vBox.prefHeightProperty().bind(gameCanvas.heightProperty());

        vBox.setAlignment(Pos.CENTER);

        resumeGameButton = new Button();
        DropShadow dropShadow = new DropShadow(10, Color.GRAY);
        resumeGameButton.setEffect(dropShadow);
        resumeGameButton.setText("Resume");
        resumeGameButton.setVisible(true);

        resumeGameButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resumeGameButton.setEffect(dropShadow);
            }
        });

        resumeGameButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resumeGameButton.setEffect(null);
            }
        });

        resumeGameButton.setOnAction(actionEvent ->
                gameScene.unpauseGame());

        exitToMenuButton = new Button();
        exitToMenuButton.setText("Return to Menu");
        exitToMenuButton.setVisible(true);

        exitToMenuButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitToMenuButton.setEffect(dropShadow);
            }
        });

        exitToMenuButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitToMenuButton.setEffect(null);
            }
        });

        exitToMenuButton.setOnAction(actionEvent -> {
            controller.switchToMenu();
            gameScene.unpauseGame();
        });

        vBox.getChildren().addAll(resumeGameButton, exitToMenuButton);
        return vBox;
    }
}
