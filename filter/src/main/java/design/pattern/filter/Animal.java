package design.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Animal Class
 */
public class Animal {
    private AnimalType type;
    private String name;
    private List<Feature> features = new ArrayList<>();
    private Gender gender;

    public Animal(AnimalType type, String name, List<Feature> features, Gender gender) {
        this.type = type;
        this.name = name;
        this.features = features;
        this.gender = gender;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override public String toString() {
        return "Animal{" +
               "gender=" + gender +
               ", name='" + name + '\'' +
               ", type=" + type +
               ", features=" + features +
               '}';
    }
}
