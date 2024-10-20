package com.solvd.laba.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConnectionPool {
    private final int size = 5;
    private final String url = "jdbc:mysql://127.0.0.1:3306/farm";
    private final String user = "root";
    private final String password = "1152";
    private LinkedList<Connection> availableConnections;
    private LinkedList<Connection> connectionsInUse;
    private static ConnectionPool INSTANCE;

    public static ConnectionPool getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }

    public synchronized Connection getConnection() {
        if (availableConnections == null) {
            try {
                initPool();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        Connection connection = availableConnections.removeLast();
        connectionsInUse.add(connection);
        return connection;
    }

    private void initPool() throws SQLException {
        availableConnections = new LinkedList<>();
        connectionsInUse = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            Connection con = DriverManager.getConnection(url, user, password);
            availableConnections.add(con);
        }
    }

    public synchronized void releaseConnection(){
        if (connectionsInUse == null || connectionsInUse.isEmpty()){
            return;
        }
        Connection connection = connectionsInUse.removeFirst();
        availableConnections.add(connection);
    }

    public synchronized boolean isAvailableConnectionsSizeEmpty(){
        if (availableConnections == null) {
            return true;
        }
        return availableConnections.isEmpty();
    }

    public synchronized boolean isConnectionsInUseSizeEmpty(){
        if (connectionsInUse == null) {
            return true;
        }
        return connectionsInUse.isEmpty();
    }
//    public static void main(String[] args) {
//        ConnectionPool connectionPool = ConnectionPool.getInstance(5, "url");
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        CompletableFuture<Void>[] futures = new CompletableFuture[7];
//
//        for (int i = 1; i < 8; i++) {
//            final int taskId = i;
//            futures[i-1] = CompletableFuture.supplyAsync(() -> {
//                Connection connection = null;
//                try {
//                    connection = connectionPool.getConnection();
//                    System.out.println("Task " + taskId + " is using a connection.");
//                    Thread.sleep(1000);
//                    return "Task " + taskId + " completed.";
//                } catch (InterruptedException | RuntimeException e) {
//                    e.printStackTrace();
//                    return "Task " + taskId + " failed.";
//                } finally {
//                    connectionPool.releaseConnection();
//                    System.out.println("Task " + taskId + " returned the connection.");
//                }
//            }, executorService).thenAccept(System.out::println);
//        }
//
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
//        allOf.join();
//        executorService.shutdown();
//        System.out.println("All connections closed.");
//    }
}
