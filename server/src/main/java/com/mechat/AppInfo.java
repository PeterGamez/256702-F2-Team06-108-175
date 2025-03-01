package com.mechat;

public class AppInfo {

    private String version = "1.0.0";
    private String javaVersion = System.getProperty("java.version");
    private String springBootVersion = org.springframework.boot.SpringBootVersion.getVersion();

    public String getVersion() {
        return version;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public String getSpringBootVersion() {
        return springBootVersion;
    }
}
