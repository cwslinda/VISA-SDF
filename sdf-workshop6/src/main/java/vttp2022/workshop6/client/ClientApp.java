package vttp2022.workshop6.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp{
    public static void main(String[] args) throws UnknownHostException, IOException {
        // variables
        System.out.println("Cookie Client");
        String[] port = args[0].split(":");
        boolean stop = false;
        InputStream is = null;
        OutputStream os = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        Socket sock =  null;



        try {
            Console cons = System.console();

            while(!stop){
                sock = new Socket(port[0], Integer.parseInt(port[1]));
                String input = cons.readLine("Send command to server >>");
                // create a new connection every time, i send a command to the server side
                is = sock.getInputStream();
                dis = new DataInputStream(is);

                os = sock.getOutputStream();
                dos = new DataOutputStream(os);

                String response = null;

                // conditions 

                // when input = stop

                if(input.equals("exit")){
                    stop = true;
                    dos.writeUTF(input);
                    dos.flush();
                }

                if(!stop){
                    try {
                        response = dis.readUTF();
                    } catch (EOFException e) {
                       // suppresee if the reading is called twice.
                    }

                    // always use a constant when you want to compare to something
                    if(null != response){
                        if("cookie-text".contains("cookie-text") || "error,".contains(response)){
                            System.out.println(response);
                            String[] cookieValue = response.split(",");
                            if(response.contains("error,")){
                                System.out.printf("Error from server >> %s\n", cookieValue[1]);
                            }
                            if(response.contains("cookie-text,")){
                                System.out.printf("Cookie from the server >> %s\n", cookieValue[1]);
                            }
                        }

                    }
                }
            }

            is.close();
            os.close();
            sock.close();


        
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
