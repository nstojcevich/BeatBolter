package platformer.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import platformer.SceneManager;

import static platformer.util.Constants.*;

public class MainMenuScene extends Scene {
    /**
     * @param gameScene Scene to be switched two on button press
     * @return scene with a single button that will switch to the game when pressed
     */
    public MainMenuScene(SceneManager controller, GameScene gameScene) {
        super(menuVBox(controller, gameScene), SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private static VBox menuVBox(SceneManager controller, GameScene gameScene) {
        BackgroundImage backgroundImage = new BackgroundImage(MAIN_SCREEN_BACKGROUND, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        VBox layout = new VBox(20);
        layout.prefWidthProperty().bind(gameScene.widthProperty());
        layout.prefHeightProperty().bind(gameScene.heightProperty());
        layout.setAlignment(Pos.CENTER);
        Button button2 = new Button("Start Game");
        button2.setOnAction(e -> {
            controller.switchToGame();
        });
        layout.getChildren().addAll(button2);
        layout.setBackground(background);
        return layout;
    }
}
