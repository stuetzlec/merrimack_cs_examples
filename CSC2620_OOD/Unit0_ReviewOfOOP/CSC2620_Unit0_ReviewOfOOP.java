package csc2620_unit0_reviewofoop;

import java.util.ArrayList;

/**
 *
 * @author stuetzlec
 */
public class CSC2620_Unit0_ReviewOfOOP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<Payable> employees = new ArrayList();
        
        employees.add( new Faculty("Stuetzle", "Chair", 1, "tenured") );
        employees.add( new Staff("Kissel", "brownnoser", 2, 60) );
        
        //Employee e = new Employee("a", "b" 2);
        //e.getPaid();
        
        for(Payable e : employees ){
            System.out.println(e.getPaid());
        }
        
//        System.out.println(employees.get(0).getPaid());
//        System.out.println(employees.get(1).getPaid());

    }
    
}
