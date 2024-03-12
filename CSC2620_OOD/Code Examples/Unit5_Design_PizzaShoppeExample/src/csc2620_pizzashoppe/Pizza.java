package csc2620_pizzashoppe;


import java.util.HashMap;

/**
 *
 * @author stetz
 */
public class Pizza extends PizzaBasedItem {

    private char size;  // L, M, or S
    private double sizeModifier = 0.0; // Increased price if bigger than a small

    /**
     * Each pizza has a base price, price per topping, and a size
     *
     * @param n The name of the calzone
     * @param b The base cost
     * @param t The price per topping inside
     * @param s The price per additional sauce
     */
    public Pizza(String n, double b, double t, char s) {
        super(n, b, t);
        this.size = s;
        if (this.size == 'M') {
            sizeModifier = 1.50;
        } else if (this.size == 'L') {
            sizeModifier = 3.00;
        }
    }

    /**
     * The total price for the pizza
     *
     * @return The total price of the pizza
     */
    @Override
    public double getTotalPrice() {
        return super.getTotalPrice() + sizeModifier;
    }

    /**
     * The toString method is used for printing receipts
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s    Size: %s\n    Total:%10.2f",
                super.toString(), getSizeString(), this.getTotalPrice());
    }

    /**
     * Helper method to convert the size character into a string
     *
     * @return S becomes "Small", etc.
     */
    private String getSizeString() {
        if (this.size == 'L') {
            return "Large";
        } else if (this.size == 'M') {
            return "Medium";
        }
        return "Small";
    }

}
