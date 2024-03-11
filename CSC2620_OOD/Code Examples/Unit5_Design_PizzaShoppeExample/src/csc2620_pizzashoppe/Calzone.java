package csc2620_pizzashoppe;

import java.util.ArrayList;

/**
 *
 * @author stuetzlec
 */
public class Calzone extends PizzaBasedItem {

    private ArrayList<String> sauces = new ArrayList();
    private double pricePerSauce;

    /**
     * Each calzone has a base price, price per topping, and a price per
     * additional sauce.
     *
     * @param n The name of the calzone
     * @param b The base cost
     * @param t The price per topping inside
     * @param s The price per additional sauce
     */
    public Calzone(String n, double b, double t, double s) {
        super(n, b, t);
        this.pricePerSauce = s;

    }

    /**
     * Adds a sauce to the list of sauces
     *
     * @param s The sauce to add
     */
    public void addSauce(String s) {
        sauces.add(s);
    }

    /**
     * The total price for the calzone
     *
     * @return The total price of the calzone
     */
    public double getTotalPrice() {
        return super.getTotalPrice() + pricePerSauce * sauces.size();
    }
    
    /**
     * The toString method is used for printing receipts
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s%s    Total:%10.2f",
                super.toString(), getSauces(), this.getTotalPrice());
    }
    
    private String getSauces() {
        String saucesString = "    Sauces:\n";
        for (String t : this.sauces) {
            saucesString += "      " + t + "\n";
        }
        return saucesString;
    }

}
