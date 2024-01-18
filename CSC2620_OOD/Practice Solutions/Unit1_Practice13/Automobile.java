package polymorph_example;

/**
 *
 * @author stuetzlec
 */
public abstract class Automobile implements Driveable, Comparable<Automobile> {
    
    @Override
    public int compareTo( Automobile a ) {
        if( this.getSpeed() < a.getSpeed() ) return -1;
        else if( this.getSpeed() > a.getSpeed() ) return 1;
        else return 0;
    }
    
    // Since we know 'this' is Drivable, we know it has a getSpeed method.
    public String toString(){
        return String.format("%d mph", this.getSpeed());
    }
}
