package design.patterns.adapter.implementations;

import design.patterns.adapter.interfaces.Army;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class DwarfArmy implements Army {

    private long noOfSoldiers;
    private long regiments;
    private String name;

    public DwarfArmy() {
    }

    public DwarfArmy(long noOfSoldiers, long regiments, String name) {
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
