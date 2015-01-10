package design.pattern.iterator;

/**
 * Iterator Design Pattern Client
 */
public class App {
    public static void main (String[] args){
        Assassin desmond = new Assassin("Desmond", 250);
        WeaponSet templarWeapons = defaultTemplarWeapons();
        desmond.equipWeapons( templarWeapons );

        System.out.println("All Weapons:");
        WeaponIterator iter = desmond.getEquippedWeapons().iterator( WeaponType.ALL );
        while( iter.hasNext() ){
            System.out.println("Equipped Weapon: " + iter.next().toString() );
        }

        System.out.println("\nOne Handed Weapons:");
        WeaponIterator oneHandedWeaponIterator = desmond.getEquippedWeapons().iterator(HandType.ONE_HANDED);
        while( oneHandedWeaponIterator.hasNext() ){
            System.out.println("Single Handed Weapon: " + oneHandedWeaponIterator.next().toString() );
        }
    }

    static WeaponSet defaultTemplarWeapons(){
        WeaponSet weapons = new WeaponSetImpl();
        weapons.addWeapon( new Weapon("Rogue Legendary Dagger", WeaponType.DAGGER, true, 10, HandType.ONE_HANDED));
        weapons.addWeapon( new Weapon("Phantom Blade", WeaponType.HAND_BLADE, false, 10, HandType.TWO_HANDED));
        weapons.addWeapon( new Weapon("Indian Knief", WeaponType.KNIEF, false, 4, HandType.ONE_HANDED));
        weapons.addWeapon( new Weapon("Borgia Blade", WeaponType.SHORT_BLADE, false, 8, HandType.ONE_HANDED));
        weapons.addWeapon( new Weapon("Sword of Eden", WeaponType.SWORD, false, 15, HandType.ONE_HANDED));
        weapons.addWeapon( new Weapon("Sturmgewehr", WeaponType.AIR_RIFLE, false, 30, HandType.TWO_HANDED));
        return weapons;
    }
}

