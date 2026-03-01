package design.pattern.filter.criteria;

import design.pattern.filter.Animal;

import java.util.List;

/**
 * Criteria Interface
 */
public interface Criteria {
    public List<Animal> meetCriteria(List<Animal> animals);
}
