package netty.NIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import netty.syncBlock.TimeServerHandler;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-09-29
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
            }
        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NTO-MultiplexerTimeServer-001").start();
    }
}
