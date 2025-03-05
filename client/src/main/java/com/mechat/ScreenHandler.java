package com.mechat;

import com.mechat.interfaces.ScreenInterface;
import com.mechat.screens.ChatScreen;
import com.mechat.screens.LoginScreen;
import com.mechat.screens.MainChatScreen;
import com.mechat.screens.MainScreen;
import com.mechat.screens.RegisterScreen;
import com.mechat.screens.SettingScreen;

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

    public static void setScreen(ScreenInterface screen) {
        Scene scene = new Scene(screen.createContent(), windowWidth, windowHeight);

        String userTheme = WindowsSettings.getWindowsTheme();

        if ("dark".equals(userTheme)) {
            scene.getStylesheets().add(screenHandler.getResource("/styles/dark-theme.css").toExternalForm());
        } else {
            scene.getStylesheets().add(screenHandler.getResource("/styles/light-theme.css").toExternalForm());
        }

        stage.setScene(scene);

        debug(scene);
    }

    private static void debug(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.isAltDown()) {
                switch (event.getCode()) {
                    case DIGIT1, NUMPAD1:
                        setScreen(new MainScreen());
                        break;
                    case DIGIT2, NUMPAD2:
                        setScreen(new RegisterScreen());
                        break;
                    case DIGIT3, NUMPAD3:
                        setScreen(new LoginScreen());
                        break;
                    case DIGIT4, NUMPAD4:
                        setScreen(new MainChatScreen());
                        break;
                    case DIGIT5, NUMPAD5:
                        setScreen(new ChatScreen());
                        break;
                    case DIGIT6, NUMPAD6:
                        setScreen(new SettingScreen());
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
