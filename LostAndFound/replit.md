# Lost & Found System

## Overview
A Java Servlet-based Lost and Found web application built with embedded Apache Tomcat. Users can view lost/found items, and admins can log in to add, update, and delete items.

## Project Architecture
- **Language**: Java 19 (GraalVM CE 22.3.1)
- **Build System**: Maven
- **Server**: Embedded Apache Tomcat 10.1.18
- **Port**: 5000 (bound to 0.0.0.0)

## Directory Structure
```
src/main/java/
  com/lostandfound/Main.java   - Entry point, embedded Tomcat launcher
  model/Item.java              - Item data model
  service/ItemManager.java     - In-memory item storage/management
  servlet/                     - HTTP servlets (AddItem, AdminLogin, AdminPanel, DeleteItem, Logout, UpdateItem, ViewItems)
src/main/webapp/
  index.html                   - Homepage
  adminLogin.html              - Admin login page
  WEB-INF/web.xml              - Web app config
LostAndFoundGit/               - Original imported source
```

## Running
The application runs via Maven:
```
mvn compile exec:java -Dexec.mainClass="com.lostandfound.Main" -q
```

## Deployment
The application is configured to run using `mvn exec:java` in production.

## Database
- **Type**: Replit PostgreSQL (persistent, survives restarts)
- **Connection**: Uses `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD` environment variables (set automatically by Replit)
- **Driver**: `org.postgresql:postgresql:42.7.2` via JDBC
- **Table**: `items` with columns: `id` (SERIAL PK), `name`, `description`, `status`, `imagePath`, `created_at`

## Recent Changes
- 2026-02-21: Initial Replit setup with embedded Tomcat, Maven build
- 2026-03-14: Migrated from SQLite (ephemeral) to Replit PostgreSQL (persistent); updated Database.java and ItemManager.java
