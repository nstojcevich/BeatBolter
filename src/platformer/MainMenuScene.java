package platformer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static platformer.util.Constants.SCREEN_HEIGHT;
import static platformer.util.Constants.SCREEN_WIDTH;

public class MainMenuScene extends Scene {
    /**
     * @param gameScene Scene to be switched two on button press
     * @return scene with a single button that will switch to the game when pressed
     */
    public MainMenuScene(SceneController controller, GameScene gameScene) {
        super(layout(controller, gameScene), SCREEN_WIDTH, SCREEN_HEIGHT);
    }


    public static VBox layout(SceneController controller, GameScene gameScene) {
        VBox layout2 = new VBox(20);
        Label label2= new Label("Main Menu");
        layout2.prefWidthProperty().bind(gameScene.widthProperty());
        layout2.prefHeightProperty().bind(gameScene.heightProperty());
        layout2.setAlignment(Pos.CENTER);
        Button button2= new Button("Go to game");
        button2.setOnAction(e -> {
            controller.switchToGame();
        });
        layout2.getChildren().addAll(label2, button2);
        return layout2;
    }
}
