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
    private static long lastUpdate = 0;
    private static int index = 0;
    private static double[] frameRates = new double[100];
    private int framesPassed = 0;
    private Text fpsText;

    public MainScene(SceneManager sceneManager) {
        super(new VBox());
        AnimationTimer frameRateMeter = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                if (lastUpdate > 0)
                {
                    framesPassed++;
                    long nanosElapsed = now - lastUpdate;
                    double frameRate = 1000000000.0 / nanosElapsed;
                    index %= frameRates.length;
                    frameRates[index++] = frameRate;
                }
                sceneManager.update(framesPassed);
                lastUpdate = now;
            }
        };

        frameRateMeter.start();
    }

    /**
     * Returns the instantaneous FPS for the last frame rendered.
     *
     * @return
     */
    public static int getInstantFPS()
    {
        return (int)Math.round((frameRates[index % frameRates.length]));
    }

    /**
     * Returns the average FPS for the last 100 frames rendered.
     * @return
     */
    public static int getAverageFPS()
    {
        double total = 0.0d;

        for (int i = 0; i < frameRates.length; i++)
        {
            total += frameRates[i];
        }

        return (int)Math.round(total / frameRates.length);
    }

    public Text fpsText() {
        fpsText =  new Text("FPS: " + getAverageFPS());
        fpsText.setEffect(null);
        fpsText.setFill(Color.BLACK);
        fpsText.setFont(Font.font("Arial", 20));
        fpsText.setX(Constants.SCREEN_WIDTH - 100);
        fpsText.setY(20);
        return fpsText;
    }

    public void updateFPS() {
        fpsText.setText("FPS: " + getAverageFPS());
    }
}
