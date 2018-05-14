package platformer;

import java.io.*;
import java.util.Scanner;

class GameManager {
    private int score = 0;
    private int highScore = 0;
    private PrintWriter writer;
    private File scoreFile = new File("src/score.txt");

    GameManager() {
        readHighScoreFromFile();
    }

    void setScore(int score) {
        this.score = score;
    }

    int getScore() {
        return score;
    }

    int getHighScore() {
        return highScore;
    }

    void addToScore(int scoreToAdd) {
        setScore(score += scoreToAdd);
    }

    void resetScore() {
        updateHighscore();
        setScore(0);
    }

    void reset() {
        resetScore();
    }

    private void updateHighscore() {
        if(score > highScore)
            highScore = score;
            writeHighScoreToFile();
    }

    private void readHighScoreFromFile() {
        int newScore = -1;
        try {
            Scanner sc = new Scanner(scoreFile);
            if(sc.hasNextInt())
                newScore = sc.nextInt();
                highScore = newScore;
            sc.close();
        } catch(FileNotFoundException e) {
            System.err.println(e);
        }
    }

    private void writeHighScoreToFile() {
        try {
            FileWriter write = new FileWriter(scoreFile, false);
            writer = new PrintWriter(write);
            writer.println(highScore);
        } catch(IOException e) {
            System.err.println(e);
        } finally {
            if(writer != null)
                writer.close();
        }
    }
}
