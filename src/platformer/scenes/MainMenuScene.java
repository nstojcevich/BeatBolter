package platformer.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import platformer.SceneManager;

import static platformer.util.Constants.SCREEN_HEIGHT;
import static platformer.util.Constants.SCREEN_WIDTH;

public class MainMenuScene extends Scene {
    /**
     * @param gameScene Scene to be switched two on button press
     * @return scene with a single button that will switch to the game when pressed
     */
    public MainMenuScene(SceneManager controller, GameScene gameScene) {
        super(layout(controller, gameScene), SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public static VBox layout(SceneManager controller, GameScene gameScene) {
        VBox layout = new VBox(20);
        Label label= new Label("Main Menu");
        layout.prefWidthProperty().bind(gameScene.widthProperty());
        layout.prefHeightProperty().bind(gameScene.heightProperty());
        layout.setAlignment(Pos.CENTER);
        Button button2= new Button("Start Game");
        button2.setOnAction(e -> {
            controller.switchToGame();
        });
        layout.getChildren().addAll(label, button2);
        return layout;
    }
}
