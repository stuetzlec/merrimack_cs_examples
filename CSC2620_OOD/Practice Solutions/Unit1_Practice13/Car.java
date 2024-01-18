package polymorph_example;

/**
 *
 * @author stuetzlec
 */
public class Car extends Automobile {

    private int topSpeed;
    private int acceleration;

    public Car(int s, int a) {
        topSpeed = s;
        acceleration = a;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    /* Commented out for part 2 of the practice
    @Override
    public int compareTo(Automobile o) {
        if( o instanceof Truck ) {
            if(((Truck)o).getSpeed() > topSpeed )
                return 1;
            else if( ((Truck) o).getSpeed() < topSpeed ) 
                return -1;
        }
        else if( o instanceof Car ) {
            if( this.topSpeed < ((Car)o).topSpeed ) return -1;
            else if( this.topSpeed > ((Car)o).topSpeed ) return 1;
        }
        return 0;
    }
    */
    
    @Override
    public int getSpeed() {
        return topSpeed;
    }

    public String toString() {
        return String.format("A car with top speed %d has speed value %s", 
                this.topSpeed, super.toString());
    }

}
