package com.example.netty;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NioApplication {
	static ExecutorService executorService = Executors.newCachedThreadPool();

	public static void main(String[] args) throws Exception {

		ServerSocket ss = new ServerSocket(10010);
		System.out.println("服务开始");
		//socket 阻塞点
		while(true){
			final Socket a = ss.accept();
			executorService.submit(()->{
				try {
					handle(a);
				}catch (Exception e){
					e.printStackTrace();
				}
			});
		}




	}

	private static void handle(Socket a) throws IOException {
		System.out.println("接收到一个服务");
		InputStream is = a.getInputStream();
		System.out.println("接受到流信息！");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		//read内容 阻塞点
		System.out.println(br.readLine());
		is.close();
		a.close();
	}
}
