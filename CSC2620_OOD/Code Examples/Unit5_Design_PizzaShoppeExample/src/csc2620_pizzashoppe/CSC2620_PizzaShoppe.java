package csc2620_pizzashoppe;

/**
 *
 * @author stuetzlec
 */
public class CSC2620_PizzaShoppe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ViewController view = new ViewController();
        
        Calzone theBigMeaty = (Calzone) view.addMenuItem("Calzone", 
                "The Big Meaty", "8.00", "1.50", "0.50" );
        theBigMeaty.addSauce("Ranch");
        theBigMeaty.addTopping("Bacon");
        theBigMeaty.addTopping("Sausage");
        theBigMeaty.addTopping("Ham");
        theBigMeaty.addTopping("Chicken");
        Pizza veggieLovers = (Pizza) view.addMenuItem("Pizza",
                "Veggie Lovers", "7.50", "1.50", "M");
        veggieLovers.addTopping("Onions");
        veggieLovers.addTopping("Peppers");
        veggieLovers.addTopping("Mushrooms");
        Sandwich daClub = (Sandwich) view.addMenuItem("Sandwich",
                "DaClub", "Bacon", "Cheddar", "Lettuce", "Tomato");
        Wrap daClubWrap = (Wrap) view.addMenuItem("Wrap",
                "DaClub Wrap", "Bacon", "Cheddar", "Spinach");
        
        // A receipt that uses the toString in all the classess
        view.printReceipt();

    }
}
