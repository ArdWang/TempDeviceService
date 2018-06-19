package com.tds.thread;

import java.io.*;
import java.net.Socket;

public class ServiceThreed extends Thread{
    private Socket socket;
    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;
    private OutputStream os;
    private PrintWriter pw;
    private String info;

    public ServiceThreed(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{

            is = socket.getInputStream();
            isr = new InputStreamReader(is,"UTF-8");
            br = new BufferedReader(isr);

            //先设置读取的数据
            //byte[] readByte = new byte[1024];
            //is.read(readByte);
            //String request = new String(readByte, "utf-8");
            //System.out.println("Server: " + request);

            while ((info=br.readLine())!=null) {
                System.out.println("客户端说 "+info+"\n");
            }
            //必须要及时关闭，因为readLine这个方法是以\r\n作为界定符的，
            //由于发送消息的那一端用的是PrintWriter的write()方法，这个方法并没加上\r\n,所以会一直等待
            socket.shutdownInput();
            //回复客户端
            os=socket.getOutputStream();
            pw=new PrintWriter(os);
            pw.write("数据接受成功!");
            pw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
            try {
                if (os != null)
                    os.close();
                if (br != null)
                    br.close();
                if (is != null)
                    is.close();// 关闭返回的 InputStream 将关闭关联套接字。
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
