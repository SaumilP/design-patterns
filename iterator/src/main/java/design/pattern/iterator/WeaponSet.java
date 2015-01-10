package design.pattern.iterator;

/**
 * Weapon Collection interface denoting functions
 */
public interface WeaponSet {
    public void addWeapon(Weapon weapon);
    public void dropWeapon(Weapon weapon);
    public WeaponIterator iterator( WeaponType type);
    public WeaponIterator iterator( HandType handType );
    public void useWeapon(Weapon weapon);
}
