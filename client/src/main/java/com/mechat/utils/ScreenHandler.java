package com.mechat.utils;

import com.mechat.interfaces.ViewInterface;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenHandler {

    private static Stage stage;
    private static double windowWidth;
    private static double windowHeight;
    private static Class<? extends ScreenHandler> screenHandler;

    public ScreenHandler(Stage stage, double windowWidth, double windowHeight) {
        ScreenHandler.stage = stage;
        ScreenHandler.windowWidth = windowWidth;
        ScreenHandler.windowHeight = windowHeight;
        ScreenHandler.screenHandler = getClass();
    }

    public static void setScreen(ViewInterface screen) {
        Scene scene = new Scene(screen.createContent(), windowWidth, windowHeight);

        String userTheme = WindowsSettings.getWindowsTheme();

        if ("dark".equals(userTheme)) {
            scene.getStylesheets().add(screenHandler.getResource("/styles/dark-theme.css").toExternalForm());
        } else {
            scene.getStylesheets().add(screenHandler.getResource("/styles/light-theme.css").toExternalForm());
        }

        stage.setScene(scene);

        if (MakeCache.isFullScreen()) {
            stage.setMaximized(true);
        } else {
            stage.setMaximized(false);
            stage.centerOnScreen();
        }

        if (!stage.isShowing()) {
            stage.show();
        }

        debug(scene);
    }

    public static void setScreen(Parent screen) {
        Scene scene = new Scene(screen, windowWidth, windowHeight);

        String userTheme = WindowsSettings.getWindowsTheme();

        if ("dark".equals(userTheme)) {
            scene.getStylesheets().add(screenHandler.getResource("/styles/dark-theme.css").toExternalForm());
        } else {
            scene.getStylesheets().add(screenHandler.getResource("/styles/light-theme.css").toExternalForm());
        }

        stage.setScene(scene);

        if (MakeCache.isFullScreen()) {
            stage.setMaximized(true);
        } else {
            stage.setMaximized(false);
            stage.centerOnScreen();
        }

        if (!stage.isShowing()) {
            stage.show();
        }

        debug(scene);
    }

    private static void debug(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.isAltDown()) {
                switch (event.getCode()) {
                    case D:
                        System.out.println("Server: " + MakeCache.getServer());
                        System.out.println("Token: " + MakeCache.getAuthToken());
                        System.out.println("Session: " + MakeCache.getSession().getId());
                        System.out.println("User: " + MakeCache.getUser());
                        System.out.println("Data: " + MakeCache.getDatas());
                        System.out.println("Chat Id: " + MakeCache.getChatId());
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
