package design.pattern.filter.criteria;

import design.pattern.filter.Animal;
import design.pattern.filter.Feature;

import java.util.ArrayList;
import java.util.List;

/**
 * Feature specific Criteria
 */
public class CriteriaFeature implements Criteria {

    private Feature feature;

    public CriteriaFeature(Feature feature) {
        this.feature = feature;
    }

    @Override public List<Animal> meetCriteria(List<Animal> animals) {
        List<Animal> criteriaWithFeature = new ArrayList<>();

        for (Animal animal : animals) {
            if(animal.getFeatures().contains( feature ) ){
                criteriaWithFeature.add(animal);
            }
        }

        return criteriaWithFeature;
    }

}
