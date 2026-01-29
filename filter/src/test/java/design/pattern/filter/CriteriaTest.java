package design.pattern.filter;

import design.pattern.filter.criteria.AndCriteria;
import design.pattern.filter.criteria.Criteria;
import design.pattern.filter.criteria.CriteriaGender;
import design.pattern.filter.criteria.CriteriaType;
import design.pattern.filter.criteria.OrCriteria;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for criteria filters.
 */
public class CriteriaTest {
    /**
     * Verifies {@code shouldFilterByGender}.
     */
    @Test
    public void shouldFilterByGender() {
        List<Animal> animals = App.prepareAnimalList();
        Criteria male = new CriteriaGender(Gender.MALE);

        List<Animal> males = male.meetCriteria(animals);

        assertEquals(6, males.size());
    }

    /**
     * Verifies {@code shouldFilterByType}.
     */
    @Test
    public void shouldFilterByType() {
        List<Animal> animals = App.prepareAnimalList();
        Criteria mammals = new CriteriaType(AnimalType.MAMMAL);

        List<Animal> mammalList = mammals.meetCriteria(animals);

        assertEquals(3, mammalList.size());
    }

    /**
     * Verifies {@code shouldCombineCriteriaWithAnd}.
     */
    @Test
    public void shouldCombineCriteriaWithAnd() {
        List<Animal> animals = App.prepareAnimalList();
        Criteria male = new CriteriaGender(Gender.MALE);
        Criteria mammals = new CriteriaType(AnimalType.MAMMAL);
        Criteria maleMammal = new AndCriteria(male, mammals);

        List<Animal> maleMammals = maleMammal.meetCriteria(animals);

        assertEquals(2, maleMammals.size());
    }

    /**
     * Verifies {@code shouldCombineCriteriaWithOr}.
     */
    @Test
    public void shouldCombineCriteriaWithOr() {
        List<Animal> animals = App.prepareAnimalList();
        Criteria males = new CriteriaGender(Gender.MALE);
        Criteria birds = new CriteriaType(AnimalType.BIRD);
        Criteria birdOrMale = new OrCriteria(birds, males);

        List<Animal> birdOrMaleAnimals = birdOrMale.meetCriteria(animals);

        assertEquals(7, birdOrMaleAnimals.size());
    }
}
