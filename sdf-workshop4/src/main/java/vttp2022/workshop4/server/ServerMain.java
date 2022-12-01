package vttp2022.workshop4.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain{

    public static void main(String[] args){
        // variables for users to input it themselves (-Dexec.args)
        String serverPort = args[0];
        String cookieFilePath = args[1];

        try {
            System.out.printf("Server Started at localhost:%s\n", serverPort);

            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(serverPort));
            Socket socket = serverSocket.accept();

            //output and input stream in bytes
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            OutputStream os = socket.getOutputStream();
            DataOutputStream dout = new DataOutputStream(os);

            String clientRequest = dis.readUTF();
            System.out.printf("Request from the client: %s", clientRequest);

            if (clientRequest.equals("get-cookie")) {
                System.out.printf("file -> %s\n", cookieFilePath);

                String randomCookie = Cookie.getCookie(cookieFilePath);
                System.out.println("cookie-text: " + randomCookie);
                dout.writeUTF("cookie-text: " + randomCookie);

            } else {
                dout.writeUTF("Invaild command!");
            }

            is.close();
            os.close();

            socket.close();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

