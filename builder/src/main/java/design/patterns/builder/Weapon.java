package design.patterns.builder;

/**
 * Weapon Enumeration
 */
public enum Weapon {
    DAGGER, SWORD, AXE, WARHAMMER, BOW;

    @Override public String toString(){
        return name().toLowerCase();
    }
}
