
import java.util.Arrays;

public class Table 
{
    private  int[][] T;
    public Table()
    {
        T= new int[3][3];
    }
    
    public Table(int[][] t)
    {
        T=t;
    }
    public void newTable(int[][]n)
    {
        T=n;
    }
    public int[][] getTable()
    {
        return  T;
    }
    public void replace(int r,int c, int value)
    {
        T[r][c]=value;
    }
    public void removeInt(int r , int c)
    {
        T[r][c]=0;
    }


    @Override
    public String toString() {
        return Arrays.toString(T);
    }
    
    
}
