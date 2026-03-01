package design.patterns.builder;

/**
 * Created by PATEL1 on 12/27/14.
 */
public enum Armor {
    CLOTHES, LEATHER, CHAIN_MAIL, PLATE_MAIL;

    @Override public String toString(){
        return name().toLowerCase().replaceAll("_", " ");
    }
}
