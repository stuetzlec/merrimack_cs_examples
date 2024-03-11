package csc2620_pizzashoppe;


/**
 *
 * @author stuetzlec
 */
public class ViewController {

    private Model model = new Model();

    public MenuItem addMenuItem(String name, String... paras) {
        return model.addMenuItem(name, paras);
    }

    public void printReceipt() {
        Double total = 0.0;
        for (MenuItem i : model.getItems()) {
            System.out.println(i);
            total += i.getTotalPrice();
        }
        System.out.println("---------------------");
        System.out.printf("$%19.2f\n", total);
    }

}
