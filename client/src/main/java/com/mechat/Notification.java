package com.mechat;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.net.URL;

import javax.swing.ImageIcon;

public class Notification {

    public static void showNotification(String caption, String text) {
        SystemTray tray = SystemTray.getSystemTray();
        URL imageUrl = Notification.class.getResource("/images/icon.png");
        Image image = new ImageIcon(imageUrl).getImage();
        TrayIcon trayIcon = new TrayIcon(image, "MeChat");
        trayIcon.setImageAutoSize(true);
        try {
            tray.add(trayIcon);
            trayIcon.displayMessage(caption, text, MessageType.NONE);
            Thread.sleep(10 * 1000);
            tray.remove(trayIcon);
        } catch (AWTException e) {
            System.err.println("TrayIcon could not be added.");
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted.");
        }
    }
}
