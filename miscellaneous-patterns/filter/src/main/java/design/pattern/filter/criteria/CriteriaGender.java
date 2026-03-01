package design.pattern.filter.criteria;

import design.pattern.filter.Animal;
import design.pattern.filter.Gender;

import java.util.ArrayList;
import java.util.List;

/**
 * Gender Criteria
 */
public class CriteriaGender implements Criteria {

    private Gender gender;

    public CriteriaGender( Gender gender) {
        this.gender = gender;
    }

    @Override public List<Animal> meetCriteria(List<Animal> animals) {
        List<Animal> maleAnimals = new ArrayList<>();

        for (Animal animal : animals) {
            if(animal.getGender() == gender ){
                maleAnimals.add(animal);
            }
        }

        return maleAnimals;
    }

}
