package vttp2022.workshop3;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

// contains methods

public class Cart {
    private List<String> contents = new LinkedList<>();
    // when you login, you will need a username
    private String username;

    public Cart(String name){
        this.username = name;
    }
    public String getUsername() {
        return username;
    }

    // in the cart, we will loop through the list to make sure that there is no duplicates
    public void add(String item){
        for(String inCart: contents)
            if(inCart.equals(item))
                return;
        contents.add(item);
    }

    // issuing a delete to the linkedlist
    public String delete(int index){
        if(index < contents.size())
            return contents.remove(index);
        return "no items to delete";
    }

    //since this method start with throwing Exception, no need to do try and catch
    // for loading is always INPUT!
    public void load(InputStream is) throws IOException {
        // taking in file as a stream, because cart content is line so we need to use readline
        // inputstreamreader cannot read line by line
        // raw file reading
        InputStreamReader isr = new InputStreamReader(is);
        // need another interface to read line by line so we used the io bufferedreader
        // actual content
        BufferedReader br = new BufferedReader(isr);
        //file has multiple lines, you need to know when to stop
        //'EOF' end of file, check if it is null. If it is null then it is the end of file
        // if and while loop, when it is only one line of code after loop, no need curly braces
        String item;
        while((item = br.readLine()) != null)
            contents.add(item);
        // always claim back the resources
        // there is an order, close the last one then onwards to the first one    
        br.close();
        isr.close();
    }

    // for save/writing a file, it is always OUTPUT!
    public void save(OutputStream os) throws IOException {
        OutputStreamWriter ows = new OutputStreamWriter(os);
        // able to write line by line to the file
        BufferedWriter bw = new BufferedWriter(ows);
        // for stream -> will always need a for loop
        for(String item: contents)
            bw.write(item + "\n");
        //when writing to the file, it is staged first instead of directing writing to files
        // for saving, extra step of flushing so that it will start on a clean state for the next save.
        // no need to have an order for closing the stream because we finish flushing it clean.
        ows.flush();
        bw.flush();
        bw.close();
        ows.close();
    }


    public List<String> getContents(){
        return contents;
    }
}