
package csc2620_unit0_reviewofoop;

/**
 *
 * @author stuetzlec
 */
public class Faculty extends Employee {
    
    private String tenureStatus;
    
    public Faculty(String name, String jobTitle, int ID, String ts) {
        super(name, jobTitle, ID);
        
        this.tenureStatus = ts;
        
    }

    @Override
    public double getPaid() {
        return 80000 / 48.0;
    }
    
}
