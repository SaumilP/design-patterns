package design.pattern.decorator;

/**
 * Created by PATEL1 on 1/1/15.
 */
public enum PlayerType {
    ALLY("Ally"), ENEMY("Enemy"), SPECTATOR("Spectator");

    private String typeValue;

    private PlayerType(String typeValue ){
        this.typeValue = typeValue;
    }
}
