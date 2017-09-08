package com.NIO.nioserver;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Title: ??????</p>
 * <p>Description: ????????????????</p>
 * @author starboy
 * @version 1.0
 */

public final class Writer extends Thread {
    private static List pool = new LinkedList();
    private static Notifier notifier = Notifier.getNotifier();

    public Writer() {
    }

    /**
     * SMS???????????????,??????????????????
     */
    public void run() {
        while (true) {
            try {
                SelectionKey key;
                synchronized (pool) {
                    while (pool.isEmpty()) {
                        pool.wait();
                    }
                    key = (SelectionKey) pool.remove(0);
                }

                // ????§Õ???
                write(key);
            }
            catch (Exception e) {
                continue;
            }
        }
    }

    /**
     * ????????????????
     * @param key SelectionKey
     */
    public void write(SelectionKey key) {
        try {
            SocketChannel sc = (SocketChannel) key.channel();
            Response response = new Response(sc);

            // ????onWrite???
            notifier.fireOnWrite((Request)key.attachment(), response);

            // ???
            sc.finishConnect();
            sc.socket().close();
            sc.close();

            // ????onClosed???
            notifier.fireOnClosed((Request)key.attachment());
        }
        catch (Exception e) {
            notifier.fireOnError("Error occured in Writer: " + e.getMessage());
        }
    }

    /**
     * ??????????,??????????????,??????????§Ö??????§Õ???
     */
    public static void processRequest(SelectionKey key) {
        synchronized (pool) {
            pool.add(pool.size(), key);
            pool.notifyAll();
        }
    }
}
