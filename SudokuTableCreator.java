



public class SudokuTableCreator
{
    private int all_ran;
    private static int counter;
    private static int r=0;
    private static int c=0;
    private static Table[][] all;
    private int[][] table = new int[3][3];
    private final Solution[][] solutions = new Solution[3][3];
    public SudokuTableCreator()
    {
        
        if( c >2)
        {
            r++;
            c=0;
        }
        set_all();
        all[r][c]=new Table(table);
        if(r==2 && c==2)
        {
            check();
        }
        c++;
        all_ran++;
        
    }
    public final void check()
    {
        try{
            int[][] collums= new int[3][9];
            int[][] rows=new int[3][9];
            boolean failsafe=false;
            for( int row=0; r<3; r++)
            {
                if (failsafe){break;}
                for(int col=0; c<3; c++)
                {
                    if (failsafe){break;}
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
                        if (failsafe){break;}
                        for (int x = 0; x < 3; x++) 
                        {
                            if (failsafe){break;}
                            // System.err.println(collums[x][col]-1);
                            // System.out.println(collums[row][x]-1);
                            if(col_nums[collums[x][col]-1]!=0||row_nums[collums[row][x]-1]!=0)
                            {
                                set_table();
                                reset_all();
                                check();
                                failsafe=true;
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
            check();
            
            return;
        }
    }
    public final void reset_all()
    {
        for (int i = 0; i < 3; i++) 
        {
            r=i;
            for (int x = 0; x < 3; x++) 
            {
             c=i;   
             table= new int[3][3];
             set_all();
             all[r][c]= all[r][c]=new Table(table);
            }
        }
    }
    public final void set_all()
    {
        boolean failsafe=false;
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
            onlyset(rows, collums);
            if (failsafe)
            {
                break;
            }
           for (int col = 0; col < 3; col++) 
           {
                
                if (failsafe)
                {
                    break;
                }
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
                    }
                    set_all();
                    
                    break;
                    
                }
           }
        }
    }
    public int set(int i,int x)
    {
        // System.out.println(Arrays.toString(solutions[i][x].getNums()));
        int t = solutions[i][x].getNums()[(int) (Math.random()*(solutions[i][x].getNums().length-1))];
        for(Solution[] arr: solutions)
        {
            for(Solution sol:arr)
            {

                if(sol.contains(sol.getNums(), t)==true)
                {
                    
                   if( !(sol.popout(t))&&!(sol.issame(solutions[i][x])))
                    {
                        
                        return  set(i, x);
                    }
                }

            }
          
        }
        solutions[i][x]=new Solution(new int[]{200,300});
        return t;
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