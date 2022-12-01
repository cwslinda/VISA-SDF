package vttp2022.httptry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerListenThread extends Thread {
    public int port;
    public ServerSocket server = null;
    Socket socket = null;
    InputStream is = null;
    OutputStream os = null;
    

    public ServerListenThread(int port) throws IOException{
        this.port =  port;
        server = new ServerSocket(port);
    }

    @Override
    public void run(){
        try {
            while(!server.isClosed() && server.isBound()){
                System.out.println("Waiting for Client to connect....");
                // accepting -> to start your browser and have the localhost:3000/index.html
                socket = server.accept();

                File file = new File("index.html");
                String html =""; // responsible for displaying your website 

                FileInputStream image = new FileInputStream("mypic.png");

                try {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);

                    String content; 
                    while((content = br.readLine()) != null){
                        html += content;
                    }
                    br.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                is = socket.getInputStream(); // for getting input from browser
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br2 = new BufferedReader(isr); // convert inputstream to bufferedreader because cannot read inputstream directly

                ArrayList<String> browserResponse = new ArrayList<>();
                String line = "";
                 while(!(line = br2.readLine()).isBlank()){
                    browserResponse.add(line); // adding browser response to an linkedlist

                 }
                 String[] list = browserResponse.get(0).split("");

                 for(String item : list){
                    System.out.println("ITEM" + item);
                 }

                 if(list[1].equals("/index.html")){
                    System.out.println("index.html");
                    os = socket.getOutputStream(); // server sending message to browser which include html and image
                    os.write(("HTTP/1.1 200 OK\nContent-Type:text/html\n\r\n").getBytes());
                    os.write(("\r\n").getBytes());
                    os.write((html).getBytes());
                    os.write("\r\n".getBytes());
                    os.write("\r\n".getBytes());
                    os.flush();
                 
                } else if (list[1].equals("/mypic.png")){
                    // after displaying the html, browser will do another get request for the image 
                    os = socket.getOutputStream();
                    os.write(("HTTP/1.1 200 OK\nContent-Type:text/html\n\r\n").getBytes());
                    os.write(("\r\n").getBytes());
                    os.write(image.readAllBytes());
                    os.flush();
                }

                System.out.println("mypic.png");

                br2.close();
                image.close();


            }
            is.close();
            os.close();
            socket.close();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}