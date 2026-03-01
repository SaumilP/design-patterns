package design.pattern.mvp;

import javax.swing.*;

/**
 * Model-View-Presenter ( MVP ) Design Pattern Client
 */
public class App {
    public static void main (String[] args){
        SwingUtilities.invokeLater( new Runnable() {
            @Override public void run() {
                View view = new View();
                view.setPresenter(new Presenter(view, new Model()));
            }
        });

    }
}

