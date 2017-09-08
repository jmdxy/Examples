package com.NIO.base.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by za-wuxiaoyu on 2017/8/18.
 */
public class BaseBuffer {

//    public static void main(String[] args) {
//      ByteBuffer byteBuffer  = ByteBuffer.allocate(5);
//        byteBuffer.put((byte) 'H').put((byte) 'H').put((byte) 'H').put((byte) 'H').put((byte) 'H');
//        System.out.println(byteBuffer.get());
//    }
    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        CharBuffer buffer = CharBuffer.allocate(100);
        while (fillBuffer(buffer)) {
            buffer.flip();
            testGet(buffer);
            buffer.clear();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
    private static void drainBuffer(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println("");
    }

    private static boolean fillBuffer(CharBuffer buffer) {
        if (index >= strings.length) {
            return (false);
        }
        String string = strings[index++];
        for (int i = 0; i < string.length(); i++) {
            buffer.put(string.charAt(i));
        }
        //缓冲区并不是多线程安全的。如果您想以多线程同时存取特定的缓冲区，您需要在存取缓冲区之前进行同步（例如对缓冲区对象进行跟踪）。

        return (true);
    }

    private static void testGet(CharBuffer buffer) {
        char[] chars ;
        while (buffer.hasRemaining()) {

            if(buffer.remaining() > 10 ) {
                chars = new char[10];
                buffer.get(chars);
            }else {
                chars = new char[buffer.remaining()+1];
                buffer.get(chars,0,buffer.remaining());
            }
            System.out.print(chars);
        }
    }

    private static void testGet2(CharBuffer buffer) {
        char[] chars = new char[Math.min(buffer.remaining() ,10)];
        while (buffer.hasRemaining()) {
                chars  = new char[Math.min(buffer.remaining() ,10)];
                buffer.get(chars);
            }
            System.out.print(chars);
    }
    private static int index = 0;
    private static String [] strings = {
                "A random string value", "The product of an infinite number of monkeys", "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly",
            // Sorry Jimi ;-)
            "Help Me! Help Me!",
    };
}
