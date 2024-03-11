package csc2620_pizzashoppe;


import java.util.ArrayList;

/**
 * An abstract class with all menu items that have toppings
 *
 * @author stuetzlec
 */
public abstract class PizzaBasedItem extends MenuItem {

    private double pricePerTopping;
    private double basePrice;
    private ArrayList<String> toppings = new ArrayList();

    /**
     * The constructor needs the pizza-based item's name and pricing info
     *
     * @param n The name of the item
     * @param b The base price of the item before toppings are applied
     * @param t The price of each individual topping
     */
    public PizzaBasedItem(String n, double b, double t) {
        super(n);
        this.basePrice = b;
        this.pricePerTopping = t;
    }

    /**
     * Adds a topping to the list of toppings
     *
     * @param t The topping to add
     */
    public void addTopping(String t) {
        toppings.add(t);
    }

    @Override
    public double getTotalPrice() {
        return basePrice + pricePerTopping * toppings.size();
    }

    /**
     * The toString method is used for printing receipts
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s\n%s",
                super.toString(), getToppings());
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
