package vttp2022.workshop6.server;

//reading, writing and closing of the data stream
//segmenting the files 

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkIO {
    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
    private DataOutputStream dos;

    public NetworkIO(Socket sock) throws IOException{
        is = sock.getInputStream();
        dis = new DataInputStream(is);
        os = sock.getOutputStream();
        dos = new DataOutputStream(os);
    }

    // method to read from the dis 
    public String read()throws IOException{
        return dis.readUTF();
    }

    // method to write on the dos
    public void write(String msg)throws IOException{
        dos.writeUTF(msg);
        dos.flush();
    }

    // method: to close all the stream
    public void close(){
        try {
            dis.close();
            is.close();
            dos.close();
            os.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 

// client send data to server 
// client -> write
// server -> read