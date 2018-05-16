package platformer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class SceneController {
    private Stage theStage;
    private GameScene gameScene;
    private Scene mainMenuScene;

    public SceneController(Group root, Canvas gameCanvas, Stage theStage) {
        this.theStage = theStage;

        GameScene gameScene = new GameScene(root, gameCanvas, this);
        Scene mainMenuScene = new MainMenuScene(this, gameScene);
        this.mainMenuScene = mainMenuScene;
        this.gameScene = gameScene;
        theStage.setScene(mainMenuScene);
    }

    public void switchToMenu() {
        theStage.setScene(mainMenuScene);
        gameScene.freezeLoop();
    }

    public void switchToGame() {
        theStage.setScene(gameScene);
        gameScene.unFreezeLoop();
    }

    public void start() {
        theStage.setScene(mainMenuScene);
        theStage.show();
    }
}
