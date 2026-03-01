package design.pattern.decorator;

/**
 * Created by PATEL1 on 1/1/15.
 */
public enum PlayStrategy {
    DEFENSIVE("Defensive"), OFFENSIVE("Offensive");

    private String value;

    private PlayStrategy(String value){
        this.value = value;
    }
}
