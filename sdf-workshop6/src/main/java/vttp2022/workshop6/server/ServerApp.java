package vttp2022.workshop6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {
        public static void main( String[] args ) throws IOException
        {
           

            int port = 3001;
            // there are two arguments needed, hence this if condition to check 
            if(args.length > 0)
                port = Integer.parseInt(args[0]);
                System.out.printf("Connect to port: %d\n", port);
            String cookieFile = args[1];

            // allocate 2 thread
            ExecutorService threadPool = Executors.newFixedThreadPool(2);

            
           ServerSocket server = new ServerSocket(port);

            while(true){
                System.out.println("Waiting for client connection");
                // accept will return us the socket
                Socket sock = server.accept();
                System.out.println("Connected....");
                // socket is closed inside the runnable 
                CookieClientHandler thr = new CookieClientHandler(sock, cookieFile);
                threadPool.submit(thr);
                System.out.println("Submitted to threadpool");
            }
        }

       
    }
