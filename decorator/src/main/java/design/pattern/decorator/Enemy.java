package design.pattern.decorator;

import java.util.Random;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class Enemy {
    private int maxHealth;
    private int currentHealth;
    private int minDamage;
    private int maxDamange;
    private int defense;
    private String name;
    private boolean isPoisoned;

    // OPERATIONS
    public Enemy randomEnemy(){
        Random random = new Random(100);
        switch( random.nextInt()%3){
            case 0: return Slime();
            case 1: return Goblin();
            case 2: return Skeleton();
            default: return Goblin();
        }
    }

    private static Enemy Slime(){
        Enemy enemy = new Enemy();
        enemy.name = "Slime";
        enemy.maxHealth = 40;
        enemy.currentHealth = enemy.maxHealth;
        enemy.minDamage = 10;
        enemy.maxDamange = 15;
        enemy.defense = 1;
        return enemy;
    }

    private static Enemy Goblin(){
        Enemy enemy = new Enemy();
        enemy.name = "Goblin";
        enemy.maxHealth = 60;
        enemy.currentHealth = enemy.maxHealth;
        enemy.minDamage = 20;
        enemy.maxDamange = 20;
        enemy.defense = 2;
        return enemy;
    }

    private static Enemy Skeleton(){
        Enemy enemy = new Enemy();
        enemy.name = "Skeleton";
        enemy.maxHealth = 100;
        enemy.currentHealth = enemy.maxHealth;
        enemy.minDamage = 20;
        enemy.maxDamange = 30;
        enemy.defense = 2;
        return enemy;
    }

    // ACCESSORS
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
        if(this.currentHealth < 0){
            this.currentHealth = 0;
        }
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamange() {
        return maxDamange;
    }

    public void setMaxDamange(int maxDamange) {
        this.maxDamange = maxDamange;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPoisoned() {
        return isPoisoned;
    }

    public void setPoisoned(boolean isPoisoned) {
        this.isPoisoned = isPoisoned;
    }
}
