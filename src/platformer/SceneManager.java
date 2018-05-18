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
    private MainScene mainScene;
    private GameScene gameScene;
    private MainMenuScene mainMenuScene;
    private Group root;

    public SceneManager(Group root, Canvas gameCanvas, Stage theStage) {
        this.theStage = theStage;
        MainScene mainScene = new MainScene(this);
        GameScene gameScene = new GameScene(root, gameCanvas, this);
        MainMenuScene mainMenuScene = new MainMenuScene(this, gameScene);
        this.mainMenuScene = mainMenuScene;
        this.gameScene = gameScene;
        this.mainScene = mainScene;
        this.root = root;
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

    public void update(int framesPassed) {
        gameScene.update(framesPassed);
        mainScene.updateFPS();
    }
}
