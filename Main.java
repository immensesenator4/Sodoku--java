public class Main
{
    public static void main(String[] args) 
    {

        
        SudokuTableCreator.set_table();
        SudokuCreator.setUp();
        SudokuTableCreator.makeBlanks(21);
        SudokuCreator.print_sodoku_board();
        
        int[][] puzzle = new int[9][9];
        int col_count=0;
        for (int i = 0; i < 3; i++) {
            
            for(int z=0; z<3; z++)
            {
                for (Table c : SudokuTableCreator.getAll()[i])
                {
                    
                    for (int f = 0; f < 3; f++) 
                    {
                        
                        
                        if (col_count>8)
                        { col_count=0;}
                        puzzle[(i*3)+z][col_count]=c.getTable()[z][f];
                        col_count++;
                    }
                
               
                }
            
            }
        }
        
        int[][] solvedPuzzle = helpers.arrayDeepCopy(puzzle);

        SudokuChecker s = new SudokuChecker();
        s.solvePuzzle(solvedPuzzle);
        helpers.printSudoku(solvedPuzzle, puzzle);
        
    }

}
