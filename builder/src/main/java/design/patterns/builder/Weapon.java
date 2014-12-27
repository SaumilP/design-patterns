package design.patterns.builder;

/**
 * Created by PATEL1 on 12/27/14.
 */
public enum Weapon {
    DAGGER, SWORD, AXE, WARHAMMER, BOW;

    @Override public String toString(){
        return name().toLowerCase();
    }
}
