package vttp2022.workshop3;

/**
 * Hello world!
 *
 */
public class App
{
    private static String defaultDb= "db";
    public static void main( String[] args )

    {
        // print out the first argument db name use to create the directory
        // the args being supplied is the pwd
        // if you remove the pwd, it will be revert back to db
        if(args.length > 0) 
            if(args[0] != null){
            System.out.println( args[0] );
            App.defaultDb =  args[0];
        }
        System.out.println( defaultDb );
        // persist and create the directory
        Repository repo = new Repository(defaultDb);
        // taking in command - controller:maybe
        Session session = new Session(repo);
        // start the session -> jump to session class
        session.start();
    }
}
