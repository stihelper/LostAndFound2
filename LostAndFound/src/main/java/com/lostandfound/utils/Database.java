package com.lostandfound.utils;

import java.sql.*;
import java.util.Properties;

public class Database {

    public static Connection connect() throws SQLException {
        String host = System.getenv("PGHOST");
        String port = System.getenv("PGPORT");
        String database = System.getenv("PGDATABASE");
        String user = System.getenv("PGUSER");
        String password = System.getenv("PGPASSWORD");

        if (host == null || database == null || user == null) {
            throw new SQLException("PostgreSQL environment variables (PGHOST, PGDATABASE, PGUSER) are not set.");
        }

        String jdbcUrl = "jdbc:postgresql://" + host + ":" + (port != null ? port : "5432") + "/" + database;

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password != null ? password : "");
        props.setProperty("sslmode", "disable");

        return DriverManager.getConnection(jdbcUrl, props);
    }

    public static void initialize() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS items (
                    id SERIAL PRIMARY KEY,
                    name TEXT NOT NULL,
                    description TEXT NOT NULL,
                    status TEXT NOT NULL,
                    "imagePath" TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
