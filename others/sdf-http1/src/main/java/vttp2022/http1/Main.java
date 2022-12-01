package vttp2022.http1;

import java.util.LinkedList;
import java.util.List;


/**
 * Main file 
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        int PORT = 3000;
        List<String> DOCROOT = new LinkedList<>();
        String[] dirs;
        
        switch(args.length){
            case 0:

                PORT = 3000;
                DOCROOT.add("./static");
                break;
        

            case 2: 
                if(args[0].toUpperCase().equals("--PORT")){
                    PORT = Integer.parseInt(args[1]);
                } else if (args[0].toUpperCase().equals("--DOCROOT")){
                        DOCROOT.clear();
                        dirs = args[1].split(":");
                        for(int i = 0; i < dirs.length; i++){
                            DOCROOT.add(dirs[i]);
                        }
                }
                break;
            case 4:
                for(int i = 0; i < 4; i ++){
                    if(args[i].toUpperCase().equals("--PORT")){
                        PORT = Integer.parseInt(args[i+1]);
                    } else if (args[i].toUpperCase().equals("--DOCROOT")){
                        DOCROOT.clear();
                        dirs = args[i+1].split(":");
                        for(int j = 0; j < dirs.length; j++){
                            DOCROOT.add(dirs[j]);
                        }
                    }
                }
                break;
        }
            HttpServer httpServer = new HttpServer(PORT, DOCROOT);
            System.out.println(PORT);
            System.out.println(DOCROOT);
            httpServer.start();
        }
    }   


