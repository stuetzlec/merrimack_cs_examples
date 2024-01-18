package polymorph_example;

/**
 *
 * @author stuetzlec
 */
public class Truck extends Automobile {

    public int horsePower;

    public Truck(int h) {
        horsePower = h;
    }

    @Override
    public int getSpeed() {
        return horsePower / 2;
    }

    /* Commented out for Part 2 of practice
    @Override
    public int compareTo(Automobile o) {
        int thisSpeed = horsePower / 2;
        int otherSpeed;
        if (o instanceof Car) {
            otherSpeed = ((Car) o).getTopSpeed();
        }
        else {
            otherSpeed = ((Truck)o).horsePower / 2;
        }
    
        return thisSpeed - otherSpeed;
    }
    */

    public String toString() {
        return String.format("A truck with horsepower %d has speed value %s", this.horsePower, super.toString());
    }
}
