import java.util.ArrayList;

public class SudokuChecker
{
    private int[] getRow(int[][] puzzle, int rowIndex)
    {
        return puzzle[rowIndex];
    }

    private int[] getCol(int[][] puzzle, int colIndex)
    {
        int[] res = new int[9];
        for(int i = 0; i < 9; i++)
        {
            res[i] = puzzle[i][colIndex];
        }
        return res;
    }

    private int[] getBox(int[][] puzzle, int rowIndex, int colIndex)
    {
        int rowTop = 3 * Math.floorDiv(rowIndex , 3);
        int colTop = 3 * Math.floorDiv(colIndex , 3);

        int[] res = new int[9];
        int i = -1;
        for(int row = rowTop; row < rowTop + 3; row++)
        {
            for(int col = colTop; col < colTop + 3; col++)
            {
                i++;
                res[i] = puzzle[row][col];
            }
        }
        //guess I could've also done a select copy but eh..
        return res;
    }

    private boolean onlySpaceForValue(int[][] puzzle, int targetValue, int rowIndex, int colIndex)
    {
        int rowTop = 3 * Math.floorDiv(rowIndex , 3);
        int colTop = 3 * Math.floorDiv(colIndex , 3);

        int unusableOtherRows = 0;
        //check rows
        for(int row = rowTop; row < rowTop + 3; row++)
        {
            if (row == rowIndex) {continue;} //don't count the space being checked
            if (puzzle[row][colIndex] > 0) //already a value there, unusable
            {
                unusableOtherRows++;
                continue;
            }
            if (contains(getRow(puzzle, row), targetValue)) //if there is already the value in the row
            {
                unusableOtherRows++;
            }
        }

        int unusableOtherCols = 0;
        //check rows
        for(int col = colTop; col < colTop + 3; col++)
        {
            if (col == colIndex) {continue;} //don't count the space being checked
            if (puzzle[rowIndex][col] > 0) //already a value there, unusable
            {
                unusableOtherCols++;
                continue;
            }
            if (contains(getCol(puzzle, col), targetValue)) //if there is already the value in the col
            {
                unusableOtherCols++;
            }
        }

        return unusableOtherRows == 2 && unusableOtherCols == 2;
    }

    private int[][] useContext(int[][] puzzle)
    {
        for(int rowIndex = 0; rowIndex < 9; rowIndex++)
        {
            for(int colIndex = 0; colIndex < 9; colIndex++)
            {
                if(puzzle[rowIndex][colIndex] > 0) {continue;} //already a number there
                
                int[] box = getBox(puzzle, rowIndex, colIndex);
                int[] row = getRow(puzzle, rowIndex);
                int[] col = getCol(puzzle, colIndex);

                
                for(int i = 1; i <= 9; i++)
                {
                    if (contains(box, i)) {continue;}//cannot have numbers already in box
                    if (contains(row, i)) {continue;}//cannot have numbers already in row
                    if (contains(col, i)) {continue;}//cannot have numbers already in col
                    
                    if (onlySpaceForValue(puzzle, i, rowIndex, colIndex))
                    {
                        puzzle[rowIndex][colIndex] = i;
                        return puzzle;
                    }
                }
            }
        }
        return puzzle;
    }

    private boolean contains(int[] vals, int targetVal)
    {
        for(int val : vals)
        {
            if(val == targetVal)
            {
                return true;
            }
        }
        return false;
    }

    private int[] getPossibleNumbers(int[] row, int[] col, int[] box)
    {
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 1; i <= 9; i++)
        {
            boolean possFromRow = !contains(row, i);
            boolean possFromCol = !contains(col, i);
            boolean possFromBox = !contains(box, i);

            if( possFromRow && possFromCol && possFromBox)
            {
                res.add(i);
            }
        }
        return helpers.intArrayFromArrayList(res);
    }

    public int[][] useDirect(int[][] puzzle)
    {
        for(int row = 0; row < 9; row++)
        {
            for(int col = 0; col < 9; col++)
            {
                if (puzzle[row][col] > 0) {continue;}

                int[] rowPoss = getRow(puzzle, row);
                int[] colPoss = getCol(puzzle, col);
                int[] boxPoss = getBox(puzzle, row, col);

                int[] possibleNums = getPossibleNumbers(rowPoss, colPoss, boxPoss);
                if(possibleNums.length == 1)
                {
                    puzzle[row][col] = possibleNums[0];
                }
            }
        }
        return puzzle;
    }

    public int[][] solvePuzzle(int[][] puzzle)
    {
        
        int noChangeCount = 0;
        int spacesLeft = helpers.countZerosIn2DArray(puzzle);

        while(helpers.arrayHasZeros(puzzle))
        {
            puzzle = useDirect(puzzle);
            puzzle = useContext(puzzle);
            if(spacesLeft == helpers.countZerosIn2DArray(puzzle)) { noChangeCount++;}
            else 
            {
                noChangeCount = 0;
                spacesLeft = helpers.countZerosIn2DArray(puzzle);
            }
            if(noChangeCount >= 3)
            {
                System.out.println("Three failed loops. not solvable: ");
                return puzzle;
            }
        }
        System.out.println("Solvable!");
        return puzzle;
    }


}