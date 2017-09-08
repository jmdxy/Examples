package com.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.Socket;

/**
 * Created by za-wuxiaoyu on 2017/7/3.
 */
public class MyClient {
    public static void main(String[] args) throws Exception{


        InetAddress a = InetAddress.getByName(null);
        System.out.println("addresss:"+a);

        Socket socket = new Socket(a,MyCServer.PORT);
        System.out.println("client:"+socket);

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

        for(int i = 0; i < 10; i ++) {
            out.println("howdy " + i);
            String str = reader.readLine();
            System.out.println(str);
        }

        out.println("EOF");

        socket.close();
        System.out.println("socket closed:"+socket);

    }

}
