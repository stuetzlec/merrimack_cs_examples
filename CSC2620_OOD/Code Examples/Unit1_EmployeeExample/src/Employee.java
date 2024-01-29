package csc2620_unit4example;

/**
 * An abstract super class for the employee hierarchy.
 * @author stuetzlec
 */
public abstract class Employee implements Comparable<Employee>, Payable {
    private String first;
    private String last;
    private String SSN;
    
    /**
     * The only constructor for Employee
     * @param f The employee's first name
     * @param l The employee's last name
     * @param ssn The employee's social security number
     */
    public Employee( String f, String l, String ssn ) {
        this.first = f;
        this.last = l;
        this.SSN = ssn;
    }
    
    // public abstract double getPaid();
    
    @Override
    public int compareTo(Employee e){
        if( e.getPaid() > this.getPaid() ) {
            return -1;
        }
        else if( e.getPaid() < this.getPaid() ){
            return 1;
        }
        return 0;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }
    
    /**
     * Every employee needs to be able to get paid, so every subclass should be
     *  able to calculate the pay for that employee.
     * @return The amount the employee gets paid in the week.
     */ 
    //public abstract double getPaid();
    
    @Override
    public String toString() {
        return String.format("---------------------\n%s: %s %s\n%s: %s", 
                "Employee", this.first, this.last,
                "SSN", this.SSN);
    }
}
