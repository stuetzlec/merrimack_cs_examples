package csc2620_unit4example;

/**
 *
 * @author stuetzlec
 */
public class OvertimeEmployee extends HourlyEmployee {
    private double overtimeRate;

    public OvertimeEmployee(String f, String l, String ssn, double rate, double ot) {
        super(f, l, ssn, rate);
        this.overtimeRate = ot;
    }
    
    @Override
    public double getPaid() {
        return overtimeRate * 15 + super.getPaid();
    }
    
    public String toString() {
        return String.format("%s\n%s: %.2f", super.toString(), "OT Rate", overtimeRate);
    }
}
