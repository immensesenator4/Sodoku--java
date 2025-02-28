
import java.util.ArrayList;





public final class SudokuTableCreator
{
    private  int counter;
    private  int r=0;
    private  int c=0;
    private static Table[][] all;
    private int[][] table = new int[3][3];
    private final Solution[][] solutions = new Solution[3][3];
    public SudokuTableCreator()
    {
        
        reset_all();
        
    }
    public final void check()
    {
        try{
            int[][] collums= new int[3][9];
            int[][] rows=new int[3][9];
            for( int row=0; r<3; r++)
            {
                for(int col=0; c<3; c++)
                {
                    for(int z =0; z<3; z++)
                    {
                        for(int y=0; y<3; y++)
                        {
                            
                            for (int x =0; x<3; x++)
                            {
                                collums[z][(y*3)+(x)]=all[y][col].getTable()[x][z];
                                rows[z][(y*3)+(x)]= all[row][y].getTable()[z][x];
                            }
                        }
                    } 
                    // System.out.println(Arrays.toString(collums[0])+Arrays.toString(collums[1])+Arrays.toString(collums[2]));
                    for (int y = 0; y < 3; y++) 
                    {
                        int[] col_nums= new int[9];
                        int[] row_nums= new int[9];
                        for (int x = 0; x < 3; x++) 
                        {
                            // System.err.println(collums[x][col]-1);
                            // System.out.println(collums[row][x]-1);
                            if(col_nums[collums[col][(y*3)+x]-1]!=0||row_nums[collums[row][(y*3)+x]-1]!=0)
                            {
                                set_table();
                                reset_all();
                                check();
                                return;
                            }
                            else
                            {
                                col_nums[collums[x][col]-1]=collums[x][col];
                                row_nums[collums[row][x]-1]=collums[row][x];
                            }
                            
                        }
                    }
                }       
            }
        }
        catch( Exception e )
        {
            set_table();
            reset_all();
            
        }
    }
    public static void makeBlanks(int amt)
    {
        int count=0;
        ArrayList<ArrayList<Integer>>  blank = new ArrayList<>();
        while (count<amt) 
        {
            int r1 =(int) (Math.random()*3);
            int c1 =(int) (Math.random()*3);
            int r2 = (int) (Math.random()*3);
            int c2 =(int) (Math.random()*3);
            if(all[r1][c1].getTable()[r2][c2]!=0)
            {all[r1][c1].removeInt(r2, c2); count++;}
            
        }
        
    }
    public final void reset_all()
    {
        for (int i = 0; i < 3; i++) 
        {
            r=i;
            for (int x = 0; x < 3; x++) 
            {
             c=x;   
             table= new int[3][3];
             set_all();
             all[r][c]= new Table(table);
             if (r==2&&c==2)
             {
                check();
             }
            }
        }
    }
    public final void set_all()
    {

        int[][] collums= new int[3][9];
        int[][] rows=new int[3][9];
        for(int z =0; z<3; z++)
        {
            for(int y=0; y<3; y++)
            {
                for (int x =0; x<3; x++)
                {
                    collums[z][(y*3)+(x)]=all[y][c].getTable()[x][z];
                    rows[z][(y*3)+(x)]= all[r][y].getTable()[z][x];
                }
            }
        }        
       
        for (int row = 0; row < 3; row++) 
        {

           for (int col = 0; col < 3; col++) 
           {
            onlyset(rows, collums);

                int[] finaly;
                int[] nums ={1,2,3,4,5,6,7,8,9};
                int count=0;
                
                for(int x=0; x<3; x++)
                {
                    for (int y = 0; y < 9; y++)
                    {
                        
                        if(contains(collums[col], nums[y])|contains(rows[row], nums[y])|contains(table[x], nums[y]))
                        {
                            
                            nums[y]=0;
                            
                        }
                    }
                }
                for(int n :nums)
                {
                    if(n!=0)
                    {
                        count++;
                    }
                }
                finaly= new int[count];
                count=0;
                for (int n :nums) 
                {
                    if(n!=0)   
                    {
                        finaly[count]=n;
                        count++;
                    }
                }
                // System.out.println(Arrays.toString(finaly)+"\n"+Arrays.toString(collums[col])+"\n"+Arrays.toString(rows[row])+"\n"+Arrays.toString(table[0])+Arrays.toString(table[1])+Arrays.toString(table[2]));
                try
                {
                    solutions[row][col]=new Solution(finaly);
                    if (table[row][col]==0){table[row][col]=solutions[row][col].getNums()[(int) (Math.random() * solutions[row][col].getNums().length)];}
                }
                catch(Exception e)
                {
                    table=new int[3][3];
                    // System.out.println(r);
                    counter++;
                    if( counter >9)
                    {
                        
                        set_table();
                        counter=0;
                    }
                    set_all();

                    
                    counter=0;
                    return;
                    
                    
                }
           }
        }
    }
    
    public void onlyset(int[][] rows,int[][] cols)
    {
        for (int row = 0; row < 3; row++) 
        {
            
           for (int col = 0; col < 3; col++) 
           {
                
                int[] nums ={1,2,3,4,5,6,7,8,9};
                int count=0;
                
                for(int x=0; x<3; x++)
                {
                    for (int y = 0; y < 9; y++)
                    {
                        
                        if(contains(cols[col], nums[y])||contains(rows[row], nums[y])||contains(table[x], nums[y]))
                        {
                            
                            nums[y]=0;
                            
                        }
                    }
                }
                for(int n :nums)
                {
                    if(n!=0)
                    {
                        count++;
                    }
                }
                if (count ==1)
                {
                    for(int n:nums)
                    {
                        if(n!=0)
                        {
                            table[row][col]=n;
                        }
                    }
                }
                
            }
        }
    }
    
    public static void set_table()
    {
        all = new Table[3][3];
        for (int i=0; i<3;i++)
        {
            for (int x=0; x<3;x++)
                {
                    all[i][x]=new Table();
                }
        }
        //  {{new Table(),new Table(),new Table()},{new Table(),new Table(),new Table()},{new Table(),new Table(),new Table()}}; 
    }
    public static Table[][] getAll() {
        return all;
    }

    public int[][] getTable() {
        return table;
    }
    

    public static  boolean contains(int[] arr,  int key) {
       for (int b: arr){if (b ==key){return true;}}return false;
    }
}