package mymackexample;

/**
 *
 * @author stuetzlec
 */
public class Faculty extends Employee {
    Boolean tenureStatus;
    Double salary;
    
    public Faculty(String n, String e, Integer id, Boolean t, Double s){
        super(n, e, id);
        
        tenureStatus = t;
        salary = s;
    }
    
    @Override
    public String getName(){
        return "Dr. " + super.getName();
    }

    @Override
    public Double getPaid() {
        return salary/24.0;
    }
    
    public String toString() {
        return String.format(super.toString() + 
                " tenured?:%20s\n   salary:%20f", 
                tenureStatus.toString(), salary);
    }

    
}

