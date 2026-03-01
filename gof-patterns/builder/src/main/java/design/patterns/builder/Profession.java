package design.patterns.builder;

/**
 * Created by PATEL1 on 12/27/14.
 */
public enum Profession {
    WARRIOR, THIEF, MAGE, PRIEST;

    @Override public String toString(){
        return name().toLowerCase();
    }
}
