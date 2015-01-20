package design.pattern.filter.criteria;

import design.pattern.filter.Animal;

import java.util.List;

/**
 * OR Criteria
 */
public class OrCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override public List<Animal> meetCriteria(List<Animal> animals) {
        List<Animal> firstCriteriaAnimals = criteria.meetCriteria( animals );
        List<Animal> otherCriteriaAnimals = otherCriteria.meetCriteria(animals);

        for (Animal animal : otherCriteriaAnimals) {
            if(!firstCriteriaAnimals.contains(animal)){
                firstCriteriaAnimals.add(animal);
            }
        }

        return firstCriteriaAnimals;
    }
}
