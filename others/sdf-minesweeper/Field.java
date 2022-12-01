// import java.util.Random;
// public class Field {

    
//     public static void main(String[] args) 
//     {

//         int fieldHidden[][] = new int[10][10];
//         int fieldVisble[][] = new int[10][10];
//         int i = 5;
//         int j = 6;
        
//             fieldVisble[i][j] = 50;
//             if(i!=0)
//             {
//                 fieldVisble[i-1][j] = fieldHidden[i-1][j];
//                 if(fieldVisble[i-1][j]==0)
//                     fieldHidden[i-1][j-1] = 50;

//                     if(j!=0)
//                     {
//                         fieldVisble[i-1][j-1] = fieldHidden[i-1][j-1];
//                         if(fieldVisble[i-1][j-1] == 0)
//                             fieldVisble[i-1][j-1] = 50;
//                     }
//             }

//             if(i!= 9)
//             {
//                 fieldVisble[i+1][j] = fieldHidden[i+1][j];
//                 if(fieldVisble[i+1][j+1]==0)
//                     fieldVisble[i+1][j+1] = 50;
//                     if(j!=9)
//                     {
//                         fieldVisble[i+1][j+1] = fieldHidden[i+1][j+1];
//                         if(fieldVisble[i+1][j+1]==0)
//                             fieldVisble[i+1][j+1] = 50;
//                     }
//             }
//             if(j !=0 )
//             {
//                 fieldVisble[i][j-1] = fieldHidden[i][j-1];
//                 if(fieldVisble[i][j-1] == 0)
//                     fieldVisble[i][j-1] = 50;
//                     if(i!=9)
//                     {
//                         fieldVisble[i+1][j-1] = fieldHidden[i+1][j-1];
//                         if(fieldVisble[i+1][j-1] == 0)
//                         fieldVisble[i+1][j-1] = 50;
//                     }

//                     if(j!=9)
//                     {
//                         fieldVisble[i][j+1] = fieldHidden[i][j+1];
//                         if(fieldVisble[i][j+1]==0)
//                             fieldVisble[i][j+1] = 50;
//                             if(i !=0)
//                                 fieldVisble[i-1][j+1] = fieldHidden[i-1][j+1];
//                                 if(fieldVisble[i-1][j+1] == 0)
//                                     fieldVisble[i-1][j+1] = 50;

//                     }
//             }
//         }
// }
