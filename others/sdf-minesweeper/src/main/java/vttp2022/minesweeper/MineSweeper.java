package vttp2022.minesweeper;

import java.io.Console;
import java.util.*;

public class MineSweeper{

    // variables for two field
    // visble is the one that user input and have user's mark -> can change
    // hidden is the one that has the bomb and doesn't change
    private int[][] fieldVisble = new int[10][10];
    private int[][] fieldHidden = new int[10][10];

        public void startGame()
        {
            System.out.println("======Welcome to Minesweeper!=====");
            setUpField(1);

            boolean flag = true;

            while(flag){
                displayVisble();
                flag = playMove();
                if(checkWin()){
                    displayHidden();
                    System.out.println("You Won!");
                    break;
                }
            }
         }

        // placement of the bombs
        public void setUpField(int diff){
                int var = 0;

                while(var != 10){
                    Random rand = new Random();
                    int i = rand.nextInt(10);
                    int j = rand.nextInt(10);
                    fieldHidden[i][j] = 100;
                    var++;
                }

                buildHidden();
        }

        public void buildHidden(){

            for(int i = 0; i<10; i++){
                for(int j = 0; j<10; j++){
                    int cnt = 0;
                    if(fieldHidden[i][j] != 100){
                        if(i!=0){
                            if(fieldHidden[i-1][j] == 100) cnt++;
                            if(j!=0){
                                if(fieldHidden[i-1][j-1] == 100) cnt++;
                            }
                        }
                        if(j!=9){
                            if(fieldHidden[i][j+1]==100) cnt++;
                            if(i!=0){
                                if(fieldHidden[i-1][j+1]==100) cnt++;
                            }
                        }
                        fieldHidden[i][j] = cnt;

                    }
                }
            }

        }

        public void displayVisble(){
            System.out.println("\n");
            System.out.print(" ");
            for(int i=0; i<10; i++)
            {
                System.out.printf("%-2s", "");
                System.out.print(" "+i);
    
            }
            System.out.print("\n");
            System.out.print("-------------------------------------------");
            System.out.print("\n");
            for(int i=0; i<10; i++)
            {
                System.out.print(i + "\t| ");
                for(int j=0; j<10; j++)
                {
                    if(fieldVisble[i][j]==0)
                    {
                        System.out.print("? ");
                    }
                    else if(fieldVisble[i][j] == 50)
                    {
                        System.out.print(" ");
                    }
                    else 
                    {
                        System.out.print(fieldVisble[i][j]);
                    }

                    System.out.print("| ");
                }

                System.out.print("\n");
            }
        }

        public boolean playMove()
        {
            Console cons = System.console();
            String input = cons.readLine("Enter your Row Number:");
            int i = Integer.parseInt(input);
            String input2 = cons.readLine("Enter your Column Number:");
            int j = Integer.parseInt(input2);


            
            if(i >  9 || i < 0 || j > 9 || j < 0 || fieldVisble[i][j] != 0)
            {
                System.out.println("Invaild input! ");
                return true;
            }

            if(fieldHidden[i][j] == 100){
                displayHidden();
                System.out.println("You stepped on a mine\n");
                System.out.println("====Game Over====");
                return false;

            }
            else if(fieldVisble[i][j] == 0)
            {
                fixVisble(i, j);
            }
            else
            {
                fixNeigbour(i,j);
            }

            return true;

        }

        public void fixVisble(int i, int j)

        // revealing the surrounding from the point that you choose
        // top left: row - 1; col - 1 (etc)
        {
            fieldVisble[i][j] = 50;

            if(i!=0)
            {   //replacing the fieldHidden with the point on fieldVisible
                fieldVisble[i-1][j] = fieldHidden[i-1][j];
                if(fieldVisble[i-1][j]==0)
                    fieldHidden[i-1][j-1] = 50;

                    if(j!=0)
                    {
                        fieldVisble[i-1][j-1] = fieldHidden[i-1][j-1];
                        if(fieldVisble[i-1][j-1] == 0)
                            fieldVisble[i-1][j-1] = 50;
                    }
            }

            if(i!= 9)
            {
                fieldVisble[i+1][j] = fieldHidden[i+1][j];
                if(fieldVisble[i+1][j+1]==0) fieldVisble[i+1][j+1] = 50;
                    if(j!=9)
                    {
                        fieldVisble[i+1][j+1] = fieldHidden[i+1][j+1];
                        if(fieldVisble[i+1][j+1]==0) fieldVisble[i+1][j+1] = 50;
                    }
            }
            if(j !=0 )
            {
                fieldVisble[i][j-1] = fieldHidden[i][j-1];
                if(fieldVisble[i][j-1] == 0) fieldVisble[i][j-1] = 50;
                    if(i!=9)
                    {
                        fieldVisble[i+1][j-1] = fieldHidden[i+1][j-1];
                        if(fieldVisble[i+1][j-1] == 0)
                        fieldVisble[i+1][j-1] = 50;
                    }
            }

            if(j!=9)
            {
                fieldVisble[i][j+1] = fieldHidden[i][j+1];
                if(fieldVisble[i][j+1]==0) fieldVisble[i][j+1] = 50;
                    if(i !=0)
                    {
                        fieldVisble[i-1][j+1] = fieldHidden[i-1][j+1];
                        if(fieldVisble[i-1][j+1] == 0)
                            fieldVisble[i-1][j+1] = 50;
                    }

            }
        }

        public void fixNeigbour(int i, int j)
        {
            Random rand = new Random();
            int x = rand.nextInt()%4;

            fieldVisble[i][j] = fieldHidden[i][j];
            
            if(x==0)
            {
                if(i!=0)
                {
                    if(fieldHidden[i-1][j]!=100)
                    {
                        fieldVisble[i-1][j] = fieldHidden[i-1][j];
                        if(fieldVisble[i-1][j]==0) fieldVisble[i-1][j] = 50;
                    }
                }
                        
                if(j!=0)
                {
                    if(fieldHidden[i][j-1] !=100)
                    {
                        fieldVisble[i][j-1] = fieldHidden[i][j-1];
                        if(fieldVisble[i][j-1]==0) fieldVisble[i][j-1] = 50;

                    }
                 }

                if(i!=0 && j!=0)
                {

                    if(fieldHidden[i-1][j-1]!=100)
                    {
                        fieldVisble[i-1][j-1] = fieldHidden[i-1][j-1];
                        if(fieldVisble[i-1][j-1] == 0) fieldVisble[i-1][j-1] = 50;
                    }
                }
                else if(x==1)
                {
                    if(i!=0)
                    {
                        if(fieldHidden[i-1][j]!=100)
                        {
                            fieldVisble[i-1][j] = fieldHidden[i-1][j];
                            if(fieldVisble[i-1][j] == 0)fieldVisble[i-1][j] = 50;
                        }
                        
                    }
                    if(j!=9)
                    {
                        if(fieldHidden[i][j+1] != 100)
                        {
                            fieldVisble[i][j+1] = fieldHidden[i][j+1];
                            if(fieldVisble[i][j+1] == 0) fieldVisble[i][j+1] = 50;
                        }
                    }
                    if(i!=0 && j!=9)
                    {
                        if(fieldHidden[i-1][j+1] !=100)
                        {
                            fieldVisble[i-1][j+1] = fieldHidden[i-1][j+1];
                            if(fieldVisble[i-1][j+1] == 0) fieldVisble[i-1][j+1] = 50;
                        }
                    }
                }
                else if(x==2)
                {
                    if(i!=9)
                    {
                        if(fieldHidden[i+1][j]!=100)
                        {
                            fieldVisble[i+1][j] = fieldHidden[i+1][j];

                        if(fieldVisble[i+1][j] == 0) fieldVisble[i+1][j] = 50;
                        }
                    }
                    if(j!=9)
                    {
                        if(fieldHidden[i][j+1] != 100)
                        {
                            fieldVisble[i][j+1] = fieldHidden[i][j+1];
                            if(fieldVisble[i][j+1] == 0) fieldVisble[i][j+1] = 50;
                        }
                    }
                    if(i!=9 && j!=9)
                    {
                        if(fieldHidden[i+1][j+1] !=100)
                        {
                            fieldVisble[i+1][j+1] = fieldHidden[i-1][j+1];
                            if(fieldVisble[i+1][j+1] == 0) fieldVisble[i+1][j+1] = 50;
                        }
                    }
                }
                else
                {
                    if(i!=9)
                    {
                        if(fieldHidden[i+1][j]!=100)
                        {
                            fieldVisble[i+1][j] = fieldHidden[i+1][j];

                        if(fieldVisble[i+1][j] == 0)
                            fieldVisble[i+1][j] = 50;
                        }
                        
                    }
                    if(j!=0)
                    {
                        if(fieldHidden[i][j-1] != 100)
                        {
                            fieldVisble[i][j-1] = fieldHidden[i][j-1];
                        if(fieldVisble[i][j-1] == 0)
                            fieldVisble[i][j-1] = 50;
                        }
                    }
                    if(i!=9 && j!=0)
                    {
                        if(fieldHidden[i+1][j-1] !=100)
                        {
                            fieldVisble[i+1][j-1] = fieldHidden[i-1][j-1];
                        if(fieldVisble[i+1][j-1] == 0)
                            fieldVisble[i+1][j-1] = 50;
                        }
                    }
                }

            }
        }

        public boolean checkWin()
        {
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++)
                {
                    if(fieldVisble[i][j] == 0)
                    {
                        if(fieldHidden[i][j] != 100)
                        {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public void displayHidden()
        {
            System.out.println("\n");
            System.out.print(" ");
            for(int i=0; i<10; i++)
        {
            
            System.out.printf("%2s", "");
            System.out.print(" "+i);

        }
        System.out.print("\n");
        System.out.print("-------------------------------------------");
        System.out.print("\n");

        for(int i=0; i<10; i++)
        {
            System.out.print(i + "\t| ");
            for(int j=0; j<10; j++)
            {   
                if(fieldHidden[i][j]==0)
                {
                   
                    System.out.print(" ");
                }
                else if(fieldHidden[i][j] == 100)
                {   
                    System.out.print("X");
                }
                else 
                {
                    System.out.print(fieldHidden[i][j]);
                }
                System.out.printf("%1s", "");
                System.out.print("| ");
            }

            System.out.print("\n");
        }
    }
        public static void main(String[] args) {
            MineSweeper M = new MineSweeper();
            M.startGame();
    
        }
}
