package CSC2620_Unit2_22Practice;

public class Pet {
    
    private final String name;
    private final int age;
    private final String species;
    
    public Pet( String n, int a, String s ) {
        species = s;
        age = a;
        name = n;
    }
    
    public String getName() { 
        return name;
    }
    
    // A toString method to show info about the pet
    @Override
    public String toString() {
        return String.format("%s:\n  Age: %d\n  Species: %s\n\n",
                name, age, species);
    }
}