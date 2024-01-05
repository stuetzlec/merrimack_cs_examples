
package csc2620_unit0_reviewofoop;

/**
 *
 * @author stuetzlec
 */
public abstract class Employee implements Payable {
    
    private String name;
    private String jobTitle;
    private int ID;

    public Employee(String name, String jobTitle, int ID) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
}
