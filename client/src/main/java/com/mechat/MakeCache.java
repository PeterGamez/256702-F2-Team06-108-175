package com.mechat;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.mechat.interfaces.ControllerInterface;

import jakarta.websocket.Session;

public class MakeCache {

    private static ArrayList<ControllerInterface> controllers;
    private static Map<String, Object> server;
    private static String authToken;
    private static Session session;
    private static Map<String, Object> user;

    public MakeCache() {
        controllers = new ArrayList<>();
        loadControllers();
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
}
