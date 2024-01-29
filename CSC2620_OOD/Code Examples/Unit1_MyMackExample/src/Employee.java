package mymackexample;

/**
 *
 * @author stuetzlec
 */
public abstract class Employee extends CommunityMember implements Payable {
    
    public Employee(String n, String e, Integer id){
        super(n, e, id);
    }    
}
