package csc2620_unit4_mvcexample;

import javax.swing.JOptionPane;

class Controller {

    private Model model;
    private View view;

    public Controller(Model m, View v) {
        model = m;
        view = v;
        initView();
    }

    public void initView() {
        view.getFirstnameTextfield().setText(model.getFirstname());
        view.getLastnameTextfield().setText(model.getLastname());
    }

    public void initController() {
        view.getFirstnameSaveButton().addActionListener(e -> saveFirstname());
        view.getLastnameSaveButton().addActionListener(e -> saveLastname());
        view.getHello().addActionListener(e -> sayHello());
        view.getBye().addActionListener(e -> sayBye());
    }

    private void saveFirstname() {
        model.setFirstname(view.getFirstnameTextfield().getText());
        JOptionPane.showMessageDialog(null, "Firstname saved : " + model.getFirstname(), "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveLastname() {
        model.setLastname(view.getLastnameTextfield().getText());
        JOptionPane.showMessageDialog(null, "Lastname saved : " + model.getLastname(), "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void sayHello() {
        JOptionPane.showMessageDialog(null, "Hello " + model.getFirstname() + " " + model.getLastname(), "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void sayBye() {
        System.exit(0);
    }
}
