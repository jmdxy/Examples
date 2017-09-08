package com.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by za-wuxiaoyu on 2017/7/3.
 */
public class MyCServer {
    public static final int PORT=8080;
    public static void main(String[] args) throws  Exception{
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Start:"+s);

        Socket socket = s.accept();
        System.out.println(
                "Connection accepted: "+ socket);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()
                        )
                ),true
        );

        while(true){
            String str = reader.readLine();
            if(str == null) {
                Thread.sleep(20);
                continue;
            }
            if("EOF".equals(str)) break;
            System.out.println("get in:"+str);
            out.print(str);
        }

        socket.close();
        s.close();

    }
}
