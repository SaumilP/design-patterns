package design.pattern.iterator;

/**
 * Assassin Class
 */
public class Assassin {
    private String name;
    private WeaponSet equippedWeapons = new WeaponSetImpl();
    private int health;

    public Assassin(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void equipAWeapon(Weapon weapon){
        equippedWeapons.addWeapon( weapon );
    }

    public void unequipAWeapon(Weapon weapon){
        equippedWeapons.dropWeapon( weapon );
    }

    public WeaponSet getEquippedWeapons() {
        return equippedWeapons;
    }

    public void equipWeapons(WeaponSet weaponSet){
        WeaponIterator iterator = weaponSet.iterator(WeaponType.ALL);
        while( iterator.hasNext() ){
            Weapon weapon = iterator.next();
            equipAWeapon(weapon);
        }
    }
}
