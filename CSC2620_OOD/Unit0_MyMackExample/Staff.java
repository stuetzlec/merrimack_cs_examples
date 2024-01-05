
package mymackexample;

/**
 *
 * @author stuetzlec
 */
public class Staff extends Employee {
    private Double payRate;
    private Integer hoursWorked;

    public Staff(String n, String e, Integer id, Double pay, Integer hours) {
        super(n, e, id);
        payRate = pay;
        hoursWorked = hours;
    }

    @Override
    public Double getPaid() {
        return payRate * hoursWorked;
    }
    
    @Override
    public String toString() {
        return String.format( super.toString() + 
                "  payRate:%20f\n    hours:%20d", 
                this.payRate, this.hoursWorked);
    }
    
}
