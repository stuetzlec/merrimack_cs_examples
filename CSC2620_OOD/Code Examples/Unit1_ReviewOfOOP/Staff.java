
package csc2620_unit0_reviewofoop;

/**
 *
 * @author stuetzlec
 */
public class Staff extends Employee {
    
    private int hoursPerWeek;

    public Staff(String name, String jobTitle, int ID, int hpw) {
        super(name, jobTitle, ID);
        
        this.hoursPerWeek = hpw;
    }

    @Override
    public double getPaid() {
        return hoursPerWeek * 37.00;
    }

    
}
