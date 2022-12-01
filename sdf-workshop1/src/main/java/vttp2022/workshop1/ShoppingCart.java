package vttp2022.workshop1;

import java.io.Console;
import java.util.*;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShoppingCart {
    private static String dirPath = "src/cartdb";
    private static String dirPath2 = "src/db";
    public static void main(String[] args) {
        System.out.println("Welcome to your shopping cart!");
        List<String> cart = new LinkedList<>();
        Console cons = System.console();
        String input;
        int delIndex;
        boolean stop = false;

        

        while(!stop){
            Path dirPathObj = Paths.get(dirPath);
            Path dirPath2Obj = Paths.get(dirPath2);

            boolean dirExists = Files.exists(dirPathObj);
            if(dirExists){
                System.out.println("Directory exist");
                Path path = Paths.get(dirPath);
            } else {
                try{
                    Files.createDirectories(dirPath2Obj);
                    System.out.println("Created db database");
                    Path path2 = Paths.get(dirPath2);
                }catch(IOException ioExceptionObj){
                    System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
                }
            }


            input = cons.readLine(">");
            String[] terms = input.split(" ");
            String cmd = terms[0];

            switch(cmd){
                case "list":
                    if (cart.size() > 0) {
                        for(int i = 0; i < cart.size(); i++){
                            System.out.printf("%d: %s\n", i + 1, cart.get(i));
                        }
                    }else{
                            System.out.println("Your cart is empty");
                        }
                        break;

                case "add":
                    String fruitStr = terms[1];
                    String fruitsReplaced = fruitStr.replace(",", " ");
                    String[] fruits = fruitsReplaced.split(" ");

                    for(int i = 0; i < fruits.length; i++){
                        boolean found = false;
                        for(int j=0; j < cart.size(); j++){
                            if(fruits[i].toUpperCase().equals(cart.get(j).toUpperCase())){
                                found = true;
                                break;
                            }
                        }
                        if(!found){
                            cart.add(fruits[i]);
                            System.out.printf("Added %s to the cart\n", fruits[i]);
                        }
                    }

                    break;
                case "del":
                    if(terms.length < 2){
                        System.out.println("Invaild command, please provide an index");
                    }else{
                        try{
                            delIndex = Integer.parseInt(terms[1]) - 1;
                            System.out.println(delIndex);
                            if(delIndex >= 0 && delIndex < cart.size()){
                                System.out.printf("Deleted %s from cart\n", cart.get(delIndex));
                                cart.remove(delIndex);
                            }else{
                                showNoSuchItemToDel();
                            }
                        }catch(NumberFormatException e){
                            showNoSuchItemToDel();
                        }
                    }
                    break;
                    case "end":
                        stop = true;
                        break;
                    default:
                        System.out.println("Invalid Command");

                    }

                }
                System.out.println("Thank you for shopping with us!");

            }

            private static void showNoSuchItemToDel(){
                System.out.println("No such item to delete");
            }
    }

