package com.mechat.utils;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.net.URL;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Notification {

    private static final Logger log = LoggerFactory.getLogger(Notification.class);

    public static void showNotification(String caption, String text) {
        SystemTray tray = SystemTray.getSystemTray();
        URL imageUrl = Notification.class.getResource("/images/main-icon.png");
        Image image = new ImageIcon(imageUrl).getImage();
        TrayIcon trayIcon = new TrayIcon(image, "MeChat");
        trayIcon.setImageAutoSize(true);
        try {
            tray.add(trayIcon);
            trayIcon.displayMessage(caption, text, MessageType.NONE);
            Thread.sleep(5 * 1000);
            tray.remove(trayIcon);
        } catch (AWTException e) {
            log.error("TrayIcon could not be added.", e);
        } catch (InterruptedException e) {
            log.error("Thread interrupted.", e);
        }
    }
}
