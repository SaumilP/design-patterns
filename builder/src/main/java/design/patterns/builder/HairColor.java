package design.patterns.builder;

/**
 * Created by PATEL1 on 12/27/14.
 */
public enum HairColor {
    WHITE, RED, BLOND, BLACK, BROWN;

    @Override public String toString(){
        return name().toLowerCase();
    }
}
