package vttp2022.workshop3;

import java.io.Console;
import java.util.List;

// holds all the execution of the switch statement
// interaction with the command

public class Session{
    public static final String LIST = "list";
    public static final String CARTS = "carts";
    public static final String ADD = "add";
    public static final String DELETE = "del";
    public static final String LOAD = "load";
    public static final String USERS = "users";
    public static final String SAVE = "save";
    public static final String END = "end";
    public static final String LOGIN = "login";

    private Repository repository;
    private Cart currCart;

    public Session(Repository repo){
        this.repository = repo;
    }
    // session.start call this method here
    public void start(){
        System.out.println("Welcome to your shopping cart!");
        System.out.println("Enter end to exit.");
        Console cons = System.console();
        boolean stop = false;
        // the cart objects hold the username, so when there is no login
        // name is given as anon.
        currCart = new Cart("anon");

        while(!stop){
            String input = cons.readLine("> ");
            String[] terms = input.split(" ");
            switch(terms[0]){
                case CARTS:
                    System.out.println("List of shopping carts");
                    // list carts db files
                    // TODO
                    break;

                case LIST:
                    System.out.printf("Items in %s's shopping cart\n",
                            currCart.getUsername());
                    printList(currCart.getContents());
                    break;

                case ADD:
                    int before = currCart.getContents().size();
                    for(int i=1; i<terms.length; i++)
                        currCart.add(terms[i]);
                    int addedCount = currCart.getContents().size() - before;
                    System.out.printf("Added %d items(s) to %s's cart",
                            addedCount, currCart.getUsername());
                    break;

                case DELETE:
                    int idx = Integer.parseInt(terms[1]);
                    String item = currCart.delete(idx-1);
                    System.out.printf("Removed %s from %s's cart",
                        item, currCart.getUsername());
                    break;

                case LOGIN:
                    currCart = new Cart(terms[1]);
                    System.out.printf("You are logged in as %s", terms[1]);
                    break;

                case USERS:
                    List<String> allCarts = repository.getShoppingCarts();
                    this.printList(allCarts);
                    break;

                case LOAD:
                    // should load the items previously that you have
                    currCart = repository.load(currCart.getUsername());
                    System.out.printf("Loaded %s's shopping cart. There are %d items in the cart\n", 
                            currCart.getUsername(), currCart.getContents().size());
                    printList(currCart.getContents());
                    break;

                case SAVE:
                    repository.save(currCart);
                    System.out.println("Done!");
                    break;

                case END:
                    stop = true;
                    break;

                default:
                    System.err.printf("Unknown input: %s\n", terms[0]);
                }
                System.out.println("");
            }
            System.out.println("Thank you for shopping wiht us");
    }

    public void printList(List<String> list){
        if(list.size() <=0){
            System.out.println("No record found!");
            return;
        }
        for(int i=0; i<list.size(); i++){
            System.out.printf("%d. %s\n", (i+1), list.get(i));
        }
    }
}