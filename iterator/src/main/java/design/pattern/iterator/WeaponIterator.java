package design.pattern.iterator;

/**
 * Iterator interface for Weapons
 */
public interface WeaponIterator {
    public boolean hasNext();
    public Weapon next();
}
