package design.pattern.filter.criteria;

import design.pattern.filter.Animal;
import design.pattern.filter.AnimalType;

import java.util.ArrayList;
import java.util.List;

/**
 * Animal Type Criteria class
 */
public class CriteriaType implements Criteria {

    private AnimalType type;

    public CriteriaType(AnimalType type) {
        this.type = type;
    }

    @Override public List<Animal> meetCriteria(List<Animal> animals) {
        List<Animal> typeAnimals = new ArrayList<>();

        for (Animal animal : animals) {
            if(animal.getType() == type ){
                typeAnimals.add(animal);
            }
        }

        return typeAnimals;
    }

}
