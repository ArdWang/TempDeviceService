package com.tds.service;

import com.tds.thread.ServiceThreed;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    private static final int port = 8059;
    //Socket通讯
    private static ServerSocket serverSocket;

    private static Socket socket;

    private static InetAddress inetAddress;

    /*
     * 	主函数 程序入口
     */
    public static void main(String[] args){
        try{
            //记录客户端的数量
            int count = 0;
            serverSocket = new ServerSocket(port);
            System.out.println("服务器已经启动,等待客户端的连接");

            //循环监听等待客户端的连接
            while(true){
                socket = serverSocket.accept();
                ServiceThreed serviceThreed = new ServiceThreed(socket);
                serviceThreed.start();

                count++; //统计客户端的数量
                System.out.println("客户端的数量: " + count);
                inetAddress = socket.getInetAddress();
                System.out.println("当前主机ip:"+inetAddress.getHostAddress());//获取客户端的iP地址

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
