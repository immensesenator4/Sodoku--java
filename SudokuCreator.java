

public class SudokuCreator 
{
    private static final  SudokuTableCreator[][] tables= new SudokuTableCreator[3][3];
    public static void setUp()
    {
        for (int r = 0; r < 3; r++) 
        {
            for (int c = 0; c < 3; c++) 
            {
                tables[r][c]= new SudokuTableCreator();
            }
            
        }
    }
    
    
    public static  void print_sodoku_board()
    {   
        for (int i = 0; i < 3; i++) {
            System.out.println("|-------|-------|-------|");
            
            for(int z=0; z<3; z++)
            {
                System.out.print("| ");
                for (SudokuTableCreator c : tables[i])
                {
                    
                for (int f = 0; f < 3; f++) 
                {
                    System.out.print(c.getTable()[z][f]+" ");
                }
                System.out.print("| ");
               
            }
            System.out.println();
        }
    }}
}
