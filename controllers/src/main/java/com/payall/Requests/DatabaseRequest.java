package com.payall.Requests;

public class DatabaseRequest {
    private String username;
    private String hostname;
    private String port;
    private String database;
    private String password;

    public DatabaseRequest() {
    }

    public DatabaseRequest(String username, String hostname, String port, String database, String password) {
        this.username = username;
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
