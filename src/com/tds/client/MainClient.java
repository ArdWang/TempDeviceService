package com.tds.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		runSocket();
	}

	private static void runSocket() throws IOException {
		final String HOST = "192.168.0.135";
		try {
			// 创建一个客户端socket
			Socket socket = new Socket(HOST, 8059);
			// 向服务器端传递信息
			OutputStream ots = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(ots);
			pw.write("用户名：admin;密码：123");
			pw.flush();
			// 关闭输出流
			socket.shutdownOutput();
			// 获取服务器端传递的数据
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("我是客户端，服务器说：" + info);
			}
			// 关闭资源
			br.close();
			isr.close();
			is.close();
			pw.close();
			ots.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
