package design.patterns.implementations;

import design.patterns.interfaces.Castle;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class ElfCastle implements Castle {

    private String name;
    private int length;
    private int width;
    private float latitude;
    private float longitude;
    private int levels;
    private int dungeons;

    public ElfCastle() {
    }

    public ElfCastle(String name, int length, int width, float latitude, float longitude, int levels, int rooms) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.latitude = latitude;
        this.longitude = longitude;
        this.levels = levels;
        this.dungeons = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public int getDungeons() {
        return dungeons;
    }

    public void setDungeons(int dungeons) {
        this.dungeons = dungeons;
    }
}
