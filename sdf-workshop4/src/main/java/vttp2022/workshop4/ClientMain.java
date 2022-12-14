package vttp2022.workshop4;


import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;



public class ClientMain{
    public static void main(String[] args) throws UnknownHostException, IOException {
        // variables 
        System.out.println("Cookie Client");
        String port = args[0];

        try {

            Socket socket = new Socket("localhost", Integer.parseInt(port));

            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            Console cons = System.console();
            String input = cons.readLine("Send command to server > ");
            dos.writeUTF(input);
            dos.flush();

            
            String response = dis.readUTF();
            //System.out.printf(">> %s\n", response);
            if(response.contains("cookie-text")){
                String[] cookieValue = response.split(" ");
                System.out.printf("Cookie from server -> %s\n", cookieValue[1]);
            }

            is.close();
            os.close();
            
            socket.close();


        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
