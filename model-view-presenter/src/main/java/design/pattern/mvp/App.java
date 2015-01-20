package design.pattern.mvp;

/**
 * Model-View-Presenter ( MVP ) Design Pattern Client
 */
public class App {
    public static void main (String[] args){
        View view = new View();
        view.setPresenter(new Presenter(view, new Model()));
    }
}

