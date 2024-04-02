package csc2620_unit7_example;

import java.util.Arrays;

/**
 * A class to store and sum a subarray of integers
 * @author stuetzlec
 */
public class Summer implements Runnable {
    
    private Integer[] nums;
    private boolean finished = false;
    private Integer sum = 0;
    
    public Summer( Integer[] _nums, int s, int e ) {
        this.nums = Arrays.copyOfRange(_nums, s, e);
    }

    @Override
    public void run() {
        this.sum = 0;
        for(int i = 0 ; i < this.nums.length ; i++){
            this.sum += this.nums[i];
        }
        finished = true;
    }
    
    /**
     * This method returns true if the summing is finished.
     * @return True if the summing is finished
    */
    public boolean isFinished() {
        return finished;
    }
    
    /**
     * This method returns the sum but only if the summation is finished
     * @return The sum of the integers in the array
     */
    public Integer getSum() {
        if( finished ) {
            return sum;
        }
        else{
            return null;
        }
    }
    
}
