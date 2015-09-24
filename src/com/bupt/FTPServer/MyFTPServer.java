package com.bupt.FTPServer;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RNA on 2015/6/28.
 */
public class MyFTPServer {

    public MyFTPServer() {
    }

    public void startServer() {
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory factory = new ListenerFactory();

        BaseUser user = new BaseUser();
        user.setName("test");
        user.setPassword("123456");
        user.setHomeDirectory("D:/test");

        int port = 2221;

        factory.setPort(port);
        // replace the default listener
        serverFactory.addListener("default", factory.createListener());


        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);

        try {
            serverFactory.getUserManager().save(user);
            FtpServer server = serverFactory.createServer();
            server.start();
        } catch (FtpException e) {
            e.printStackTrace();
        }

    }


    public void config() {

    }

    public static void main(String[] args) {
        MyFTPServer ftpServer = new MyFTPServer();
        ftpServer.startServer();
    }
}
