/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaMVCControllers;

import JavaMVCViews.*;
import JavaMVCModels.*;

/**
 *
 * @author AfzaalAhmad
 */
public class HelloWorldController {
    
    public void startApplication() {
        // View the application's GUI
        HelloWorldView view = new HelloWorldView();
        view.setVisible(true);
    }
    
    public String getMessage() {
        try {
            HelloWorldModel model = new HelloWorldModel();
            return model.getData();
        } catch (Exception er) {
            return "There was an error.";
        }
    }
    
    public boolean writeMessage(String message) {
        try {
            HelloWorldModel model = new HelloWorldModel();
            return model.writeData(message);
        } catch (Exception er) {
            return false;
        }
    }
}
