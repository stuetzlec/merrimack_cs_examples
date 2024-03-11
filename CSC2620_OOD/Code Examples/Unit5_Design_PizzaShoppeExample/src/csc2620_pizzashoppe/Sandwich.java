package csc2620_pizzashoppe;


import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author stetz
 */
public class Sandwich extends SandwichBasedItem {

    private ArrayList<String> toppings = new ArrayList();

    /**
     * Constructor for a sandwich
     *
     * @param n Name of the sandwich
     * @param m Type of meat on the sandwich
     * @param c Type of cheese on the sandwich
     * @param toppings A list of all additional toppings, costing $0.25 each
     */
    public Sandwich(String n, String m, String c, String... toppings) {
        super(n, m, c);
        this.toppings.addAll(Arrays.asList(toppings));
    }

    /**
     * Toppings on the sandwich just cost an additional $0.25 each
     *
     * @return The total cost of the sandwich
     */
    @Override
    public double getTotalPrice() {
        return super.getTotalPrice() + 0.25 * toppings.size();
    }

    /**
     * The toString method is used for printing receipts
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s\n%s    Total:%10.2f",
                super.toString(), getToppings(), this.getTotalPrice());
    }

    /**
     * A method to return the toppings as a single string
     *
     * @return The list of toppings as a single string with newlines
     */
    private String getToppings() {
        String toppingString = "";
        for (String t : this.toppings) {
            toppingString += "    " + t + "\n";
        }
        return toppingString;
    }
}
