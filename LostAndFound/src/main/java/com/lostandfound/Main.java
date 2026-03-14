package com.lostandfound;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

import servlet.*;
import com.lostandfound.utils.Database;

public class Main {
    public static void main(String[] args) throws Exception {
        Database.initialize();
        Tomcat tomcat = new Tomcat();

        int port = 5000;
        tomcat.setPort(port);
        tomcat.setHostname("0.0.0.0");
        tomcat.getConnector().setProperty("address", "0.0.0.0");

        String webappDir = new File("src/main/webapp").getAbsolutePath();
        Context ctx = tomcat.addWebapp("", webappDir);

        ctx.setAllowCasualMultipartParsing(true);

        Tomcat.addServlet(ctx, "viewItems", new ViewItemsServlet());
        ctx.addServletMappingDecoded("/viewItems", "viewItems");

        Tomcat.addServlet(ctx, "addItem", new AddItemServlet());
        ctx.addServletMappingDecoded("/addItem", "addItem");

        Tomcat.addServlet(ctx, "adminLogin", new AdminLoginServlet());
        ctx.addServletMappingDecoded("/adminLogin", "adminLogin");

        Tomcat.addServlet(ctx, "adminPanel", new AdminPanelServlet());
        ctx.addServletMappingDecoded("/adminPanel", "adminPanel");

        Tomcat.addServlet(ctx, "deleteItem", new DeleteItemServlet());
        ctx.addServletMappingDecoded("/deleteItem", "deleteItem");

        Tomcat.addServlet(ctx, "updateItem", new UpdateItemServlet());
        ctx.addServletMappingDecoded("/updateItem", "updateItem");

        Tomcat.addServlet(ctx, "logout", new LogoutServlet());
        ctx.addServletMappingDecoded("/logout", "logout");

        tomcat.start();
        System.out.println("Lost & Found server started on http://0.0.0.0:" + port);
        tomcat.getServer().await();
    }
}
