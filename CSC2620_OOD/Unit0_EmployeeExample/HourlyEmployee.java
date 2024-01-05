package csc2620_unit4example;

/**
 *
 * @author stuetzlec
 */
public class HourlyEmployee extends Employee {

    private double payRate;

    public HourlyEmployee(String f, String l, String ssn, double rate) {
        super(f, l, ssn);
        this.payRate = rate;
    }

    @Override
    public double getPaid() {
        return payRate * 40;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s: $%.2f", super.toString(), "Pay rate", payRate);
    }

}
