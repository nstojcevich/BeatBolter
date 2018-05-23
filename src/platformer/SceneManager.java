package platformer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import platformer.scenes.GameScene;
import platformer.scenes.MainMenuScene;
import platformer.scenes.MainScene;

public class SceneManager {
    private Stage theStage;
    private GameScene gameScene;
    private MainMenuScene mainMenuScene;

    public SceneManager(Group root, Canvas gameCanvas, Stage theStage) {
        this.theStage = theStage;
        MainScene mainScene = new MainScene(this);
        GameScene gameScene = new GameScene(root, gameCanvas, this);
        MainMenuScene mainMenuScene = new MainMenuScene(this);
        this.mainMenuScene = mainMenuScene;
        this.gameScene = gameScene;
        root.getChildren().add(mainScene.fpsText());
        theStage.setScene(gameScene);
    }

    public void switchToMenu() {
        theStage.setScene(mainMenuScene);
    }

    public void switchToGame() {
        gameScene.resetGame();
        theStage.setScene(gameScene);
    }

    public void start() {
        theStage.setScene(mainMenuScene);
        theStage.show();
    }

    public Scene getCurrentScene() {
        return theStage.getScene();
    }

    public void update() {
        gameScene.update();
    }
}
