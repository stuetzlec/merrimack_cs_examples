package csc2620_pizzashoppe;


/**
 *
 * @author stuetzlec
 */
public abstract class MenuItem {

    private String name;

    public MenuItem(String n) {
        this.name = n;
    }

    /**
     * This is an abstract method because every menu item needs to be able to
     * calculate its total price.
     *
     * @return The total price of the menu item
     */
    public abstract double getTotalPrice();

    /**
     * The toString method is used for printing receipts
     *
     * @return The name of the item
     */
    @Override
    public String toString() {
        return this.name;
    }
}
