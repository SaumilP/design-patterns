package design.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * WeaponSet Iterator implementation
 */
public class WeaponSetImpl implements WeaponSet {

    private List<Weapon> weaponList;

    public WeaponSetImpl() {
        weaponList = new ArrayList<>();
    }

    @Override public void addWeapon(Weapon weapon) {
        weaponList.add( weapon );
    }

    @Override public void dropWeapon(Weapon weapon) {
        weaponList.remove( weapon );
    }

    @Override public WeaponIterator iterator(WeaponType type) {
        return new WeaponIteratorImpl(type, weaponList);
    }

    @Override public WeaponIterator iterator(HandType handType) {
        return new WeaponIteratorImpl( handType, weaponList );
    }

    @Override public void useWeapon(Weapon weapon) {
        System.out.println( String.format("Using Weapon[%s]", weapon.toString()));
    }

    private class WeaponIteratorImpl implements WeaponIterator{
        private WeaponType type = WeaponType.NONE;
        private HandType handType = HandType.UNKNOWN;
        private List<Weapon> weapons;
        private int position;

        private WeaponIteratorImpl(WeaponType type, List<Weapon> weapons) {
            this.type = type;
            this.weapons = weapons;
        }

        private WeaponIteratorImpl(HandType handType, List<Weapon> weapons) {
            this.handType = handType;
            this.weapons = weapons;
        }

        @Override public boolean hasNext() {
            while( position < weapons.size() ){
                Weapon weapon = weapons.get(position);
                if( type != WeaponType.NONE &&
                    ( weapon.getType().equals( type ) || type.equals(WeaponType.ALL) )){
                    return true;
                } else if( handType != HandType.UNKNOWN &&
                           (weapon.getHandType().equals( handType ) || handType.equals(HandType.TWO_HANDED))){
                    return true;
                } else {
                    position++;
                }
                if( weapon.getType().equals( type ) || type.equals(WeaponType.ALL) ){
                    return true;
                }
            }
            return false;
        }

        @Override public Weapon next() {
            Weapon weapon = weapons.get(position);
            position++;
            return weapon;
        }
    }
}
