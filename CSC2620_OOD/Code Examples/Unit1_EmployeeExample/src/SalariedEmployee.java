package csc2620_unit4example;

/**
 *
 * @author stuetzlec
 */
public class SalariedEmployee extends Employee {
    private double salary;
    
    public SalariedEmployee(String f, String l, String ssn, double s ) {
        super(f, l, ssn);
        this.salary = s;
    }
    
    //@Override
    public double getPaid() {
        return salary / 48;
    }
    
    @Override
    public String toString() {
        return String.format("%s\n%s: $%.2f", super.toString(), "Salary", salary );
    }
}
