package com.NIO.base.buffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by za-wuxiaoyu on 2017/8/23.
 */
public class ChannelT {
    public static void main(String[] args)  throws IOException{
        String[] s = {"1","2","2","3","4","5","6","7","9"};
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        try {
            doCopy(source,dest);
        }catch(InterruptedException e) {
            System.out.println(source.isOpen());
            System.out.println(dest.isOpen());
            System.out.println("error");
        }
        finally
        {
            source.close();
            dest.close();
        }


    }

    private static void doCopy(ReadableByteChannel source,WritableByteChannel dest) throws IOException,InterruptedException{
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(16*1024);
        while(source.read(byteBuffer) != -1){
            byteBuffer.flip();
            byteBuffer.mark();
            String inputString  = new String(getBuffer(byteBuffer));
            if("exit\n\u0000".equals(inputString)) {
                Thread.currentThread().interrupt();//调用interrupt 会关闭当前线程使用的通道。
            }
            byteBuffer.reset();
            dest.write(byteBuffer);
            byteBuffer.compact();
        }
        byteBuffer.flip();
        while(byteBuffer.hasRemaining()){
            dest.write(byteBuffer);
        }
    }

    private static byte[] getBuffer(ByteBuffer buffer){
        byte[] chars = null;
        while (buffer.hasRemaining()) {
            if(buffer.remaining() > 10 ) {
                chars = new byte[10];
                buffer.get(chars);
            }else {
                chars = new byte[buffer.remaining()+1];
                buffer.get(chars,0,buffer.remaining());
            }
        }
        return chars;
    }
}
