package com.NIO.base.buffer;

import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
/**
 * Created by za-wuxiaoyu on 2017/8/23.
 */
public class Marketing {
    private static final String DEMOGRAPHIC = "d:/blahblah.txt";
    // "Leverage frictionless methodologies"
    public static void main(String[] argv) throws Exception {
        int reps = 10;
        if (argv.length > 0) {
            reps = Integer.parseInt(argv[0]);
        }
        FileOutputStream fos = new FileOutputStream(DEMOGRAPHIC);
        GatheringByteChannel gatherChannel = fos.getChannel();
        // Generate some brilliant marcom, er, repurposed content
        ByteBuffer [] bs = utterBS(reps);
        // Deliver the message to the waiting market
        while (gatherChannel.write (bs) > 0) {
            // Empty body // Loop until write( ) returns zero
             }
         System.out.println ("Mindshare paradigms synergized to " + DEMOGRAPHIC);
         fos.close();
    }

    private static String[] col1 = {"Aggregate", "Enable", "Leverage", "Facilitate", "Synergize", "Repurpose", "Strategize", "Reinvent", "Harness"};
    private static String[] col2 = {"cross-platform", "best-of-breed", "frictionless", "ubiquitous", "extensible", "compelling", "mission-critical", "collaborative", "integrated"};
    private static String[] col3 = {"methodologies", "infomediaries", "platforms", "schemas", "mindshare", "paradigms", "functionalities", "web services", "infrastructures"};
    private static String newline = System.getProperty("line.separator");

    private static ByteBuffer[] utterBS(int howMany) throws Exception {
        List list = new LinkedList();
        for (int i = 0; i < howMany; i++) {
            list.add(pickRandom(col1, " "));
            list.add(pickRandom(col2, " "));
            list.add(pickRandom(col3, newline));
        }
        ByteBuffer[] bufs = new ByteBuffer[list.size()];
        list.toArray(bufs);
        return (bufs);
    }
    private static Random rand = new Random( );

    private static ByteBuffer pickRandom(String[] strings, String suffix) throws Exception {
        String string = strings[rand.nextInt(strings.length)];
        int total = string.length() + suffix.length();
        ByteBuffer buf = ByteBuffer.allocate(total);
        buf.put(string.getBytes("US-ASCII"));
        buf.put(suffix.getBytes("US-ASCII"));
        buf.flip();
        return (buf);
    }
}
