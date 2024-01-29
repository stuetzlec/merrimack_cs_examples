package csc2620_debugging;

/**
 *
 * @author stuetzlec
 */
class TestClass {
    
    private int demo;
    public String tester;
    
    public TestClass( int _n, String word ){
        demo = _n;
        tester = word;
        for( int i = 100 ; i > 0 ; i-- ){
            demo -= i;
        }
        
        demo = 33;
        tester = "Test This";
    }
    
}
