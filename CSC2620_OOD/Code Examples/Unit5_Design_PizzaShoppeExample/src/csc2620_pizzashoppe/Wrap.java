package csc2620_pizzashoppe;


/**
 * This class will *not* need a getTotalPrice method because it inherits the one
 * from SandwichBasedItem and doesn't need to make additions to it. All wraps
 * cost exactly $10.99.
 *
 * @author stetz
 */
public class Wrap extends SandwichBasedItem {

    private String wrapType;

    /**
     * The Wrap constructor which sets up the type of wrap used
     *
     * @param n The name of the wrap
     * @param m The meat put in the wrap
     * @param c The cheese put in the wrap
     * @param t The type of wrap used
     */
    public Wrap(String n, String m, String c, String t) {
        super(n, m, c);
        this.wrapType = t;
    }

    /**
     * The toString method is used for printing receipts
     *
     * @return
     */
    public String toString() {
        return String.format("%s\n  %s\n    Total:%10.2f", super.toString(),
                "wrap: " + this.wrapType, this.getTotalPrice());
    }
}
