package platformer;

import javafx.application.Application;

import java.security.AccessControlException;

public class StartGame {

    public static void main(String[] args) {
        try {
            System.setProperty("quantum.multithreaded", "false");
        } catch (AccessControlException e) {
            System.err.println(e);
        }

        Application.launch(Main.class, args);
    }
}