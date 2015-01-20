package design.pattern.mvp;

/**
 * Presenter class
 */
public class Presenter {
    private View view;
    private Model model;

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void login(String password){
        String result = "Incorrect password";
        if( model.getPassword().equals(password)){
            result = "Correct Password";
        }

        view.updateStatusLabel( result );
    }
}
