package design.patterns.implementations;

import design.patterns.interfaces.Army;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class ElfArmy implements Army {

    private long noOfSoldiers;
    private long regiments;
    private String name;

    public ElfArmy() {
    }

    public ElfArmy(long noOfSoldiers, long regiments, String name) {
        this.noOfSoldiers = noOfSoldiers;
        this.regiments = regiments;
        this.name = name;
    }

    public long getNoOfSoldiers() {
        return noOfSoldiers;
    }

    public void setNoOfSoldiers(long noOfSoldiers) {
        this.noOfSoldiers = noOfSoldiers;
    }

    public long getRegiments() {
        return regiments;
    }

    public void setRegiments(long regiments) {
        this.regiments = regiments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
