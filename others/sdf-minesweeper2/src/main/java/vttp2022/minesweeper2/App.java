package vttp2022.minesweeper2;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
            int[][] fieldVisble = new int[10][10];
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
                System.out.print(i + " | ");
                for(int j=0; j<10; j++)
                {
                    if(fieldVisble[i][j]==0)
                    {
                        System.out.print("? ");
                    }
                    else if(fieldVisble[i][j] == 50)
                    {
                        System.out.print("  ");
                        
                        
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
    }

