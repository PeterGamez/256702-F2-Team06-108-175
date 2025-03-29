package com.mechat.websocket;

import jakarta.websocket.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mechat.MakeCache;

@ClientEndpoint
public class WebSocketClient {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void connect() {
        String serverIp = Objects.toString(MakeCache.getServer().get("serverIp"));
        String serverPort = Objects.toString(MakeCache.getServer().get("serverPort"));
        String uri = "ws://" + serverIp + ":" + serverPort + "/ws";
        String token = Objects.toString(MakeCache.getAuthToken());
        String authHeader = "Bearer " + token;

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        ClientEndpointConfig.Configurator configurator = new ClientEndpointConfig.Configurator() {

            @Override
            public void beforeRequest(Map<String, List<String>> headers) {
                headers.put("Authorization", Collections.singletonList(authHeader));
            }
        };

        ClientEndpointConfig config = ClientEndpointConfig.Builder.create()
                .configurator(configurator)
                .build();

        try {
            container.connectToServer(new WebSocketEndpoint(), config, new URI(uri));
        } catch (Exception e) {
        }
    }

    public static void reconnect() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                Session session = MakeCache.getSession();
                if (session == null || !session.isOpen()) {
                    connect();
                } else {
                    scheduler.shutdown();
                }
            } catch (Exception e) {
                System.err.println("Failed to reconnect");
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    public static void close() {
        try {
            Session session = MakeCache.getSession();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String message) {
        try {
            Session session = MakeCache.getSession();
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
