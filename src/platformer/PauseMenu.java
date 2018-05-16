package platformer;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class PauseMenu extends VBox{

    public PauseMenu(SceneController controller, GameScene gameScene, Canvas gameCanvas) {
        super(vBox(controller, gameScene, gameCanvas));
    }

    private static VBox vBox(SceneController controller, GameScene gameScene, Canvas gameCanvas) {
        Button exitToMenuButton, resumeGameButton;

        VBox vBox = new VBox(10);
        vBox.prefWidthProperty().bind(gameCanvas.widthProperty());
        vBox.prefHeightProperty().bind(gameCanvas.heightProperty());

        vBox.setAlignment(Pos.CENTER);

        resumeGameButton = new Button();
        resumeGameButton.setText("Resume");
        resumeGameButton.setVisible(true);
        resumeGameButton.setOnAction(actionEvent ->
                gameScene.unpauseGame());

        exitToMenuButton = new Button();
        exitToMenuButton.setText("Return to Menu");
        exitToMenuButton.setVisible(true);
        exitToMenuButton.setOnAction(actionEvent -> {
            controller.switchToMenu();
            gameScene.unpauseGame();
        });

        vBox.getChildren().addAll(resumeGameButton, exitToMenuButton);
        return vBox;
    }
}
