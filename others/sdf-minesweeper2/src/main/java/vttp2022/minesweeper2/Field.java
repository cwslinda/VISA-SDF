package vttp2022.minesweeper2;

public class Field{
    private static int[][] fieldShow = new int[10][10];
    private int[][] fieldHidden = new int[10][10];

    public static void displayField()
    {
        System.out.println("Welcome to MineSweeper");
    
        for(int i=0; i<10; i++)
        {
            System.out.print(" "+i);
        }

        System.out.print("\n");

        for(int i=0; i<10; i++)
        {
            System.out.println(i + " | ");
            for(int j=0; j<10; j++)
            {
                if(fieldShow[i][j]==0)
                {
                    System.out.print("?");
                }

                else if(fieldShow[i][j] == 50)
                {
                    System.out.print(" ");
                }
                else 
                {
                    System.out.println(fieldShow[i][j]);
                }
            }

           
        }

    }

    public static void main(String[] args) {
        displayField();
        
    }
}