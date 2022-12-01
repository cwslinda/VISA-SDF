package vttp2022.http1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.print.attribute.standard.RequestingUserName;

public class HttpClientConnection implements Runnable{
    private Socket socket;
 

    public HttpClientConnection(Socket socket){
        this.socket = socket;
    }

    @Override
    // reads the http request + respond + close the connection 
    public void run() {
        System.out.println("Client Connection is live now.");

        try {
            //open connections to the socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new  PrintStream(new BufferedOutputStream(socket.getOutputStream()));

            // reading of filename from the first inputline "GET /filename.html"
            // treat as a file not found if not in this format
            String s = in.readLine();
            System.out.println(s); // log request
          
            // attempt to serve the file
            // catch fileexceptionnotfound and return http error 404 Not Found
            String fileName = "";
            // breaking down string into token -> I am going = "i" "am" "going"
            StringTokenizer st = new StringTokenizer(s);
            try {

                // parse filename from the get command 
                if(st.hasMoreElements() && st.nextToken().equalsIgnoreCase("GET") && st.hasMoreElements()){                    fileName = st.nextToken();
                    System.out.println("Client's request: " + fileName);
                        
            } else {
                    // bad request
                    throw new FileNotFoundException();
            }   
                // append trailing "/" with "index.html"
                // fileName -> depending which folder your index.html is in 
                if(fileName.endsWith("/")) 
                    fileName += "./static/index.html";
                
                // removal of the trailing "/ " with index.html
                // localhost:8080/ -> getting  rid of this
                while(fileName.indexOf("/") == 0){
                    fileName = fileName.substring(1);
                // for operating system where JVM is able to determine which one is the best for that OS
                // portability
                fileName = fileName.replace('/', File.separator.charAt(0));
                }
                 
                // check for illegal characters to prevent access to the superdirectories ->  security
                if(fileName.indexOf("...") >= 0 || fileName.indexOf(':') >=0 || fileName.indexOf('|') >= 0)
                    throw new FileNotFoundException();


                //  checking to see if the path given is a vaild directory or path
                // if directory is requested and the trailing / is missing
                // send client an http request to append it
                if(new File(fileName).isDirectory() && new File(fileName).isFile()){
                    fileName = fileName.replace('\\', '/');
                    out.print("HTTP/1.0 301 Moved Permanently\r\n"+
                    "Location: /"+fileName+"/\r\n\r\n");
                    out.close();
                    return;
                } 

                // open the file 
                InputStream f = new FileInputStream(fileName);

                // the file type of what is being received and print the http header
                String mimeType="text/plain";
                if (fileName.endsWith(".html") || fileName.endsWith(".htm"))
                mimeType="text/html";
                else if (fileName.endsWith(".css"))
                // if you want css to be run, this is needed  to add  in
                mimeType="text/css";
                else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))
                mimeType="image/jpeg";
                else if (fileName.endsWith(".gif"))
                mimeType="image/gif";
                else if (fileName.endsWith(".class"))
                mimeType="application/octet-stream";
                out.print("HTTP/1.0 200 OK\r\n"+
                "Content-type: "+mimeType+"\r\n\r\n");

                // sending the file contents to the client, then close the connection
                byte[] a = new byte[4096];
                int n;
                while((n=f.read(a)) > 0)
                    out.write(a, 0, n);
                out.close();
            f.close();

            } catch (FileNotFoundException x) {
                out.println("HTTP/1.0 404 Not Found\r\n"+
                            "Content-type: text/html\r\n\r\n"+
                            "<html><head></head><body>"+fileName+" not found</body></html>\n");
        out.close();
       
      }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        
    }
}
