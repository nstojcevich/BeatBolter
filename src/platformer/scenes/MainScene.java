package platformer.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import platformer.SceneManager;
import platformer.util.Constants;

public class MainScene extends Scene {
    private static int index = 0;
    private static double[] frameRates = new double[100];
    private Text fpsText;

    public MainScene(SceneManager sceneManager) {
        super(new VBox());
        AnimationTimer frameRateMeter = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                sceneManager.update();
            }
        };

        frameRateMeter.start();
    }

    /**
     * Returns the instantaneous FPS for the last frame rendered.
     *
     * @return
     */
    public static int getFPS()
    {
        return (int)Math.round((frameRates[index % frameRates.length]));
    }

    public Text fpsText() {
        fpsText =  new Text("FPS: " + "TEMP");
        fpsText.setEffect(null);
        fpsText.setFill(Color.BLACK);
        fpsText.setFont(Font.font("Arial", 20));
        fpsText.setX(Constants.SCREEN_WIDTH - 100);
        fpsText.setY(20);
        return fpsText;
    }

    public void updateFPS() {
        fpsText.setText("FPS: " + getFPS());
    }
}
