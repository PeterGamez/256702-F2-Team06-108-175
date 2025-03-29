package com.mechat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.mechat.interfaces.ControllerInterface;

import jakarta.websocket.Session;

public class MakeCache {

    private static boolean isFullScreen = false;
    private static ArrayList<ControllerInterface> controllers;
    private static Map<String, Object> server;
    private static String authToken;
    private static Session session;
    private static Map<String, Object> user;
    private static List<Map<String, Object>> data;
    private static String chatId;

    public MakeCache() {
        controllers = new ArrayList<>();
        loadControllers();

        data = new ArrayList<>();
    }

    private void loadControllers() {
        try {
            Reflections reflections = new Reflections("com.mechat.controller");

            Set<Class<? extends ControllerInterface>> controllerClasses = reflections.getSubTypesOf(ControllerInterface.class);

            for (Class<? extends ControllerInterface> controllerClass : controllerClasses) {
                controllers.add(controllerClass.getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isFullScreen() {
        return isFullScreen;
    }

    public static void setFullScreen(boolean isFullScreen) {
        MakeCache.isFullScreen = isFullScreen;
    }

    public static <T extends ControllerInterface> T getController(Class<T> controllerClass) {
        for (ControllerInterface controller : controllers) {
            if (controllerClass.isInstance(controller)) {
                return controllerClass.cast(controller);
            }
        }
        return null;
    }

    public static Map<String, Object> getServer() {
        return server;
    }

    public static void setServer(Map<String, Object> server) {
        MakeCache.server = server;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static void setAuthToken(String authToken) {
        MakeCache.authToken = authToken;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        MakeCache.session = session;
    }

    public static Map<String, Object> getUser() {
        return user;
    }

    public static void setUser(Map<String, Object> user) {
        MakeCache.user = user;
    }

    public static List<Map<String, Object>> getDatas() {
        return data;
    }

    public static Object getData(String key) {
        for (Map<String, Object> item : data) {
            if (item.containsKey(key)) {
                return item.get(key);
            }
        }
        return null;
    }

    public static void setData(String key, Object value) {
        for (Map<String, Object> item : data) {
            if (item.containsKey(key)) {
                item.put(key, value);
                return;
            }
        }
        Map<String, Object> newItem = new java.util.HashMap<>();
        newItem.put(key, value);
        data.add(newItem);
    }

    public static String getChatId() {
        return chatId;
    }

    public static void setChatId(String chatId) {
        MakeCache.chatId = chatId;
    }
}
