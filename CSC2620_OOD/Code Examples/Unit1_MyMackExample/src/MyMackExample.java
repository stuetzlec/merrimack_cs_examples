package mymackexample;

import java.util.ArrayList;

/**
 *
 * @author stuetzlec
 */
public class MyMackExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Payable> warriors = new ArrayList();

        //CommunityMember m = new CommunityMember("Barty", "bartyT@merrimack.edu", 1);
        Student s = new Student("Sammy", "sammyc@merrimack.edu", 2, 3.1);

        warriors.add(s);

        //Employee e = new Employee("Chris", "stuetzlec@merrimack.edu", 3, 3.4);
        Faculty f = new Faculty("Zach", "kisselz@merrimack.edu", 4, true, 48.0);
        Staff st = new Staff("Chris", "stuetzlec@merrimack.edu", 3, 24.1, 2);

        warriors.add(f);
        warriors.add(st);

        for (int i = 0; i < warriors.size(); i++) {
            if (warriors.get(i) instanceof Payable) {
                payroll(warriors.get(i));
            }
        }

        System.out.println(s.toString());
    }

    public static void payroll(Payable e) {
        System.out.printf("Paid: %s\n%30s\n", e.toString(), "= $" + e.getPaid());
    }

}
