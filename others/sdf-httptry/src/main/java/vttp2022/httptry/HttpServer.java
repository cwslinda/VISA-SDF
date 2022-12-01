package vttp2022.httptry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class HttpServer {
    private ServerSocket serverSoc = null;
    private ArrayList<Socket> sockets = new ArrayList<>();

    


public static void main(String[] args) throws IOException {
    HttpServer server = new HttpServer(3000);
    boolean cont = true;
    while(cont){
        Request newRequest = server.AcceptSocket();
        Response newResponse = server.generateResponse(newRequest);
        cont = server.Response(newResponse);
    }
}

public HttpServer(int port) throws IOException {
    serverSoc = new ServerSocket(port);
}

public Request AcceptSocket() throws IOException{
    sockets.add(serverSoc.accept());
    return new Request(sockets.get(sockets.size()-1).getInputStream());
}
}