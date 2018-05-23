package platformer.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import platformer.SceneManager;

import static platformer.util.Constants.*;

public class MainMenuScene extends Scene {
    /**
     * @return scene with a single button that will switch to the game when pressed
     */
    public MainMenuScene(SceneManager controller) {
        super(menuVBox(controller), SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    /**
     *
     * @param controller the scene controller used to switch scenes based on button presses
     * @return layout VBox containing a background image and main menu buttons
     */
    private static VBox menuVBox(SceneManager controller) {
        BackgroundImage backgroundImage = new BackgroundImage(MAIN_SCREEN_BACKGROUND, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        Button button1 = new Button("Instructions");
        button1.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Instructions");
            Scene instructions = new Scene(instructionsVBox());
            stage.setScene(instructions);
            stage.show();
        });
        button1.setTranslateX(60);
        button1.setTranslateY(175);

        Button button2 = new Button("Start Game");
        button2.setOnAction(e -> {
            controller.switchToGame();
        });
        button2.setTranslateX(-40);
        button2.setTranslateY(224);

        layout.getChildren().addAll(button2, button1);
        layout.setBackground(background);
        return layout;
    }

    private static VBox instructionsVBox() {
        VBox instructionsVBox = new VBox(20);
        instructionsVBox.setBackground(null);
        instructionsVBox.setAlignment(Pos.CENTER);
        Label label1 = new Label("**==Instructions==**");
        label1.setFont(Font.font("Serif", 24));

        Label label2 = new Label("*Game Description*");
        label2.setFont(Font.font("Serif", 16));

        Label label3 = new Label("Beat Bolter is a game where your goal is to dodge the obsticles by jumping or ducking");
        label3.setFont(Font.font("Serif", 16));

        Label label4 = new Label("under them. The character will move on it's own at a constant speed. All you have to do  ");
        label4.setFont(Font.font("Serif", 16));

        Label label5 = new Label("is jump and duck and enjoy the background beats! ");
        label5.setFont(Font.font("Serif", 16));

        Label label6 = new Label("*Controls to the Game*");
        label6.setFont(Font.font("Serif", 16));

        Label label7 = new Label("Up Arrow ---> Jump");
        label7.setFont(Font.font("Serif", 16));

        Label label8 = new Label("Down Arrow ---> Duck");
        label8.setFont(Font.font("Serif", 16));

        instructionsVBox.getChildren().addAll(label1, label2, label3, label4, label5, label6, label7, label8);
        instructionsVBox.setVisible(true);

        return instructionsVBox;
    }

}
