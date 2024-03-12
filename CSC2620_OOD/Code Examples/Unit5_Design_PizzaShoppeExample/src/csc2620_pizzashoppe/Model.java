package csc2620_pizzashoppe;


import java.util.ArrayList;

/**
 *
 * @author stuetzlec
 */
public class Model {
    private ArrayList<MenuItem> items = new ArrayList();
    
    public Model(){
        
    }
    
    public MenuItem addMenuItem(String item, String... paras ){
        MenuItem i = null;
        if( item.equals( "Calzone" ) ) {
            i = new Calzone( paras[0], Double.parseDouble(paras[1]),
                    Double.parseDouble(paras[2]), Double.parseDouble(paras[3]) );
        } 
        else if( item.equals( "Pizza" ) ) {
            i = new Pizza( paras[0], Double.parseDouble(paras[1]),
                    Double.parseDouble(paras[2]), paras[3].charAt(0) );
        } 
        else if( item.equals( "Sandwich" ) ) {
            i = new Sandwich( paras[0],paras[1],paras[2],paras[3],paras[4]);
        }
        else if( item.equals( "Wrap" ) ) {
            i = new Wrap(paras[0], paras[1], paras[2], paras[3] );
        }
        items.add(i);
        return i;
    }
    
    public ArrayList<MenuItem> getItems(){
        return items;
    }
    
}
