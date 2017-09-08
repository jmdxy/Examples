package com.NIO.base.buffer;

import java.nio.*;
import java.nio.channels.Channel;

/**
 * Created by za-wuxiaoyu on 2017/8/23.
 */
public class BufferCharView {
    public static void main(String[] argv) throws Exception {
        byte b = 127;
        byte a = (byte) (b+1);
        System.out.println(a);
        ByteBuffer byteBuffer = ByteBuffer.allocate(5).order(ByteOrder.BIG_ENDIAN);
        IntBuffer intBuffer1 = byteBuffer.asIntBuffer();
        ByteBuffer byteBuffercopy =  byteBuffer.duplicate();
        byteBuffer.put(0,(byte) 0);
        byteBuffer.put(1,(byte) 0);
        byteBuffer.put(2,(byte) 1);
        byteBuffer.put(3,(byte) 13);
        byteBuffer.put(4,(byte) 0);
        print(intBuffer1);
        System.out.println("intBuffer1 as Intbuffer 执行get()"+Integer.toHexString(intBuffer1.get()));
        print(intBuffer1);
        print(byteBuffercopy);
        System.out.println("byteBuffercopy 执行get():"+  Integer.toHexString(byteBuffercopy.get()));
        print(byteBuffercopy);
        IntBuffer intBuffer =byteBuffercopy.asIntBuffer();
        print(intBuffer);
        System.out.println(Integer.toHexString(intBuffer.get()));
        print(intBuffer);
        byteBuffer.put(1,(byte) 1);
        byteBuffer.put(2,(byte) 1);
        byteBuffer.put(3,(byte) 1);
        IntBuffer charBuffer2 = byteBuffer.asIntBuffer();
        print(charBuffer2);
        System.out.println(Integer.toHexString(charBuffer2.get()));
        print(charBuffer2);
        print(intBuffer);
        System.out.println(Integer.toHexString(intBuffer.get()));
        print(intBuffer);
        println(byteBuffer);
        println(intBuffer1);





//        ByteBuffer byteBuffer = ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);
//        CharBuffer charBuffer = byteBuffer.asCharBuffer();
//        // Load the ByteBuffer with some bytes
//        byteBuffer.put(0, (byte) 0);
//        byteBuffer.put(1, (byte) 'H');
//        byteBuffer.put(2, (byte) 0);
//        byteBuffer.put(3, (byte) 'i');
//        byteBuffer.put(4, (byte) 0);
//        byteBuffer.put(5, (byte) '!');
//        byteBuffer.put(6, (byte) 0);
//        println(byteBuffer);
//        println(charBuffer);
    }
    // Print info about a buffer

    private static void print(Buffer buffer) {
        System.out.println("pos=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity() );
    }
    private static void println(Buffer buffer) {
        System.out.println("pos=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity() + ": '" + buffer.toString() + "'");
    }
}
