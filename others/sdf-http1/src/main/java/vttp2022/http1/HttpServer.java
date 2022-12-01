package vttp2022.http1;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer{
    private int PORT;
    private ServerSocket serverSocket = null;
    private List<String> DOCROOT;

    public  HttpServer(int PORT, List<String> DOCROOT){
        this.PORT = PORT;
        this.DOCROOT = DOCROOT;

    }

    public void start(){
        

        int count = 0;
        try {
            serverSocket = new ServerSocket(PORT);
            ExecutorService threadPool = Executors.newFixedThreadPool(3);
            System.out.printf("Connecting on localhost: %s\n", PORT);

            while(true){
                System.out.println("Waiting for Client to connect....");
                Socket socket = serverSocket.accept();
                count++;
                System.out.printf("Thread #%s\n", count);

                HttpClientConnection httpClientConnection = new HttpClientConnection(socket);
                threadPool.submit(httpClientConnection);
                System.out.println("Submitted to threadpool");
                
            }
            
        } catch (Exception e) {
                System.err.println(e.getMessage());
               }
    }

    }