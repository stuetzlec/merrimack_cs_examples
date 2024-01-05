package csc2620_unit4example;

public class CSC2620_Unit4Example {

    public static void main(String[] args) {        
        // Create an instance of each subclass of Employee
        HourlyEmployee bill = new HourlyEmployee("Bill", "Nye", "333-33-3333", 10.75);
        System.out.println(bill);
        bill.getPaid();
        SalariedEmployee neil = new SalariedEmployee("Neil", "Tyson", "444-44-4444", 55000.02);
        System.out.println(neil);
        OvertimeEmployee stein = new OvertimeEmployee("Al", "Einstein", "555-55-5555", 11.00, 17.50);
        System.out.println(stein);
        HourlyEmployee h = stein;
        
        // Because Employee is abstract, you cannot create an instance of it
        //Employee e = new Employee("Bill", "White", "333-33-2222");
        
        // Polymorphism allows us to point superclass references at subclass instances
        Payable[] emps = new Payable[1000];
        emps[0] = bill;
        emps[1] = neil;
        emps[2] = stein;
        
        payEmployees(emps);
    }
    
    private static void payEmployees( Payable[] emps ){
        for( int i = 0 ; i < 3 ; i++ ) {
            System.out.println( emps[i].getPaid() );
        }
    }
}
