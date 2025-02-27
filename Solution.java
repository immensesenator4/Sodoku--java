
import java.util.ArrayList;
import java.util.Arrays;

public class Solution
{
    private int[] nums;
    private int selected;
    public Solution(int[] num)
    {
        nums=num;
        
    }

    public int getSelected() {
        return selected;
    }
    
    public void addnum(int l)
    {
        int[] newlist = new int[nums.length+1];
        int count=0;
        for (int i :nums) {
            newlist[count]=i;
            count++;
        }
        newlist[count]=l;
        nums=newlist;
    }
    public int[] getNums() {
        return nums;
    }

    public void setNums(int[] nums) {
        this.nums = nums;
    }
    
    public int[] isSamepopped(Solution othr)
    {
        ArrayList<Integer> returnList= new ArrayList<>();
        for (int elem : othr.nums) {
            for (int n:nums) {
                

                if (!(elem==n)&&!(returnList.contains(n)))
                {
                    returnList.add(n);
                }
            }
        }
        
        System.out.println("is "+returnList.isEmpty()+" when array is "+Arrays.toString(returnList.toArray()));
         if (returnList.isEmpty())
         {
            System.out.println("returning "+Arrays.toString(nums));
            return nums;
         }
         else
         {
            int[] Returnlist=new int[returnList.size()];
            int count=0;
            for(Integer e : returnList)
            {
                Returnlist[count]=e;
                count++;
            }
            return Returnlist;
         }
    
        
    }
    public boolean popout(int l)
    {
        if(contains(nums, l))
        {
            ArrayList<Integer> newlist = new ArrayList<>();
            for (int elem : nums)
            {
                if (elem!=l)
                {
                    newlist.add(elem);
                    System.out.println(elem);
                }
                
            }
            System.out.println(newlist.isEmpty());
            if (newlist.isEmpty())
            {
                return false;
            }
            else
            {
                int[] numlist= new int[newlist.size()];
                int count=0;
                for(Integer e : newlist)
                {
                    numlist[count]=e;
                    count++;
                }
                nums=numlist;
               
                return true;
            }
        }
        else
        {
            return false;
        }
        

    }
    public boolean isequal(Solution othr)
    {
        return this.equals(othr);
    }
    public boolean issame(Solution othr)
    {
        return Arrays.equals(othr.nums, this.nums);
    }
    public  boolean contains(int[] arr,  int key) {
        for (int b: arr){if (b ==key){return true;}}return false;
     }

}
