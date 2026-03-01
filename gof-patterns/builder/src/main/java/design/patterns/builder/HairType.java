package design.patterns.builder;

/**
 * Created by PATEL1 on 12/27/14.
 */
public enum HairType {
    BALD, SHORT, CURLY, LONG_STRAIGHT, LONG_CURLY;

    @Override public String toString(){
        return name().toLowerCase().replaceAll("_", " ");
    }
}
