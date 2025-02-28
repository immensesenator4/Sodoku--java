

public class SudokuCreator 
{
    public static void setUp()
    {
        SudokuTableCreator c = new SudokuTableCreator();
    }

    
    
    public static  void print_sodoku_board()
    {   
        for (int i = 0; i < 3; i++) {
            System.out.println("|-------|-------|-------|");
            
            for(int z=0; z<3; z++)
            {
                System.out.print("| ");
                for (Table c : SudokuTableCreator.getAll()[i])
                {
                    
                    for (int f = 0; f < 3; f++) 
                    {
                        System.out.print(c.getTable()[z][f]+" ");
                    }
                    System.out.print("| ");
               
                }
            System.out.println();
            }
        
        }
        System.out.println("|-------|-------|-------|");
    }
}
