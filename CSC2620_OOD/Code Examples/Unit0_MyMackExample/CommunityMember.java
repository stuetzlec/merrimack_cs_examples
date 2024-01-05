package mymackexample;

/**
 *
 * @author stuetzlec
 */
public abstract class CommunityMember {

    private String name;
    private String email;
    private Integer ID;

    public CommunityMember(String n, String e, Integer id) {
        this.name = n;
        this.email = e;
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }


    @Override
    public String toString() {
        return String.format("%s\n   e-mail:%20s\n       ID:%20d\n",
                this.name, this.email, this.ID);
    }
}
