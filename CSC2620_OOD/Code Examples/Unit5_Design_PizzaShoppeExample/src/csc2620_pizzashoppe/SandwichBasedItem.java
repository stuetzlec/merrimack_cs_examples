package csc2620_pizzashoppe;


import java.util.ArrayList;

/**
 *
 * @author stetz
 */
public class SandwichBasedItem extends MenuItem {

    private String meat;
    private String cheese;

    public SandwichBasedItem(String n, String m, String c) {
        super(n);
        this.meat = m;
        this.cheese = c;
    }

    @Override
    public double getTotalPrice() {
        return 10.99;
    }

    public String toString() {
        return String.format("%s\n  %s\n  %s",
                super.toString(), "with " + meat, "with " + cheese);
    }

}
