package design.patterns.adapter.implementations;

import design.patterns.adapter.interfaces.King;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class ElfKing implements King {
    private String name;
    private String knownFor;
    private String regionName;

    public ElfKing() {
    }

    public ElfKing(String name, String knownFor, String regionName) {
        this.name = name;
        this.knownFor = knownFor;
        this.regionName = regionName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(String knownFor) {
        this.knownFor = knownFor;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
