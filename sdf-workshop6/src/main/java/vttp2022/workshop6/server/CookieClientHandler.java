package vttp2022.workshop6.server;

import java.io.IOException;
import java.net.Socket;

public class CookieClientHandler implements Runnable{
    private Socket sock;
    private String cookieFile;

    public CookieClientHandler(Socket s, String cookieFile){
        this.sock = s;
        this.cookieFile = cookieFile;
    }

    // all runnable have to overide with the run method 

    @Override
    public void run() {
        System.out.println("Starting a client thread");
        try {
            // take in any command from the client side
            NetworkIO netIO = new NetworkIO(sock);
            String req = "";
            String randomCookieResp = "";
            while(!req.equals("exit")){
                // use netIO to read command coming from the client side
                req = netIO.read();
                System.out.printf("[client] %s\n", req);
                if (req.trim().equals("exit"))
                    break;
                
                if(req.trim().equals("get-cookie")){
                    System.out.printf("file -> %s\n", this.cookieFile);

                    randomCookieResp = Cookie.getCookie(this.cookieFile);
                    // write down and then send back to the client
                    netIO.write("cookie-text," + randomCookieResp);
                    break;

                } else {
                    netIO.write("invalid comment");
                    break;
                }
        

            }
            // calling from the networkIO method close will close all the stream
            // pass down from the main programme, need to close because client is the one who decided when to close
            netIO.close();
            sock.close();
            
            System.out.println("Exiting the thread !");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}