package design.pattern.mvp;

/**
 * Class representing model
 */
public class Model {
    private String password;

    public Model() {
        this.password = "topsecretpassword";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
