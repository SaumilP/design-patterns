package design.pattern.decorator;

import design.pattern.decorator.armors.Armor;
import design.pattern.decorator.item.Item;
import design.pattern.decorator.weapons.Weapon;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class Player {
    private String name;
    private PlayerType type;
    private int maxHealth;
    private int currentHealth;
    private Weapon equippedWeapon;
    private Armor equippedHead, equippedHands, equippedBody, equippedFeet;

    // -------- OPERATIONS
    public boolean isItemEquipped(Item item){
        if( item.getClass() == Weapon.class ){
            if ( item.getName().equalsIgnoreCase( equippedWeapon.getName() )){
                return true;
            }
        } else if ( item.getClass() == Armor.class ){
            Armor armor = (Armor)item;
            if(armor == equippedBody ||
               armor == equippedFeet ||
               armor == equippedHands ||
               armor == equippedHead){
                return true;
            }
        }
        return false;
    }

    public void equipWeapon(Weapon weapon){
        equippedWeapon = weapon;
    }

    public void unEquipWeapon(Weapon weapon){
        if ( weapon != null && equippedWeapon != null ){
            if ( equippedWeapon.getClass() == weapon.getClass() ){
                equippedWeapon = weapon;
            }
        }
    }

    public void equipArmor(Armor armor){
        switch (armor.getType() ){
            case BODY_ARMOR: equippedBody = armor; break;
            case FEET_ARMOR: equippedFeet = armor; break;
            case HANDS_ARMOR: equippedHands = armor; break;
            case HEAD_ARMOR: equippedHead = armor; break;
            default: break;
        }
    }

    public void unEquipArmor(Armor armor){
        switch (armor.getType() ){
            case BODY_ARMOR: equippedBody = null; break;
            case FEET_ARMOR: equippedFeet = null; break;
            case HANDS_ARMOR: equippedHands = null; break;
            case HEAD_ARMOR: equippedHead = null; break;
            default: break;
        }
    }

    // ----- ACCESSORS

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;

        if (this.currentHealth < 0) {
            this.currentHealth = 0;
        }

        if (this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }
}
