package mymackexample;

/**
 *
 * @author stuetzlec
 */
public class Student extends CommunityMember implements Payable {
    private Double gpa;
    
    public Student(String n, String e, Integer id, Double g){
        // Call the constructor of the superclass CommunityMember
        super(n, e, id); 
        
        this.gpa = g;
    }
    
    @Override
    public String toString(){
        return super.toString() + 
                String.format("      GPA:%20f", this.gpa);
    }

    @Override
    public Double getPaid() {
        return 13.25 * 8;
    }
    
}
