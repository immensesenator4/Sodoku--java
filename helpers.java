import java.util.ArrayList;

public class helpers {
    public static int[] intArrayFromArrayList(ArrayList<Integer> vals)
    {
        int[] res = new int[vals.size()];
        for(int i = 0; i < vals.size(); i++)
        {
            res[i] = vals.get(i);
        }
        return res;
    }

    public static int countValuesInArray(int[] arr, int targetVal)
    {
        int count = 0;
        for(int val : arr)
        {
            if(val == targetVal) {count++;}
        }
        return count;
    }

    public static boolean arrayHasZeros(int[] arr)
    {
        for(int val : arr) 
        {
            if(val == 0) {return true;}
        }
        return false;
    }
    public static boolean arrayHasZeros(int[][] arr)
    {
        for(int[] row : arr) 
        {
            if(arrayHasZeros(row)) {return true;}
        }
        return false;
    }

    public static int countZerosIn2DArray(int[][] arr)
    {
        int count = 0;
        for(int[] row: arr) 
        {
            for(int val : row)
            {
                if(val == 0) {count++;}
            }
        }
        return count;
    }

    public static void printSudoku(int[][] arr, int[][] origArr)
    {

        String horizBorder = "|";
        for(int i = 0; i < 3; i++)
        {
            horizBorder += "---------|";
        }
        
        System.out.println(horizBorder);
        for (int r = 0; r < 3; r++) {
            
        
            for(int row = 0; row < 3; row++) 
            {
                
                for (int c = 0; c < 3; c++) 
                {
                    
                    System.out.print("|");
                    
                    for(int col = 0; col < 3; col++)
                    {
                        int val = arr[(r*3)+row][(c*3)+col];
                        if(val == origArr[(r*3)+row][(c*3)+col]) {System.out.print(" " + val + " ");}
                        else {System.out.print(" \u001B[32m" + val + "\u001B[0m ");}
                    }
                    
                }
                System.out.println("|");
                
            }
            System.out.println(horizBorder);
        }
    }

    public static int[][] arrayDeepCopy(int[][] arr)
    {
        int[][] res = new int[arr.length][arr[0].length];
        for(int row = 0; row < arr.length; row++)
        {
            for(int col = 0; col < arr[0].length; col++)
            {
                res[row][col] = arr[row][col];
            }
        }
        return res;
    }
}
