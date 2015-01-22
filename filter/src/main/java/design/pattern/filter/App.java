package design.pattern.filter;

import design.pattern.filter.criteria.AndCriteria;
import design.pattern.filter.criteria.Criteria;
import design.pattern.filter.criteria.CriteriaGender;
import design.pattern.filter.criteria.CriteriaType;
import design.pattern.filter.criteria.OrCriteria;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
/**
 * Criteria/Filter design pattern Client
 */
public class App {
    public static void main(String[] args){
        Criteria male = new CriteriaGender(Gender.MALE);
        Criteria female = new CriteriaGender(Gender.FEMALE);
        Criteria mammal = new CriteriaType(AnimalType.MAMMAL);
        Criteria bird = new CriteriaType(AnimalType.BIRD);
        Criteria maleMammal = new AndCriteria( male, mammal );
        Criteria birdOrMale = new OrCriteria( bird, male);

        List<Animal> animals = prepareAnimalList();
        System.out.println("Males: ");
        printAnimals( male.meetCriteria(animals));

        System.out.println("\nFemales: ");
        printAnimals( female.meetCriteria(animals));

        System.out.println("\nMale Mammal: ");
        printAnimals(maleMammal.meetCriteria(animals));

        System.out.println("\nBird or Male: ");
        printAnimals(birdOrMale.meetCriteria(animals));
    }

    private static void printAnimals(List<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    static List<Animal> prepareAnimalList(){
        EnumSet<Feature> mammalFeautres = EnumSet.of( Feature.HAS_BACKBONE, Feature.CHAMBERED_HEART, Feature.HAVE_LUNGS );
        EnumSet<Feature> reptileFeautres = EnumSet.of( Feature.IS_COLD_BLOODED, Feature.IS_VERTEBRATE );
        EnumSet<Feature> aquaticFeautres = EnumSet.of( Feature.HAS_SCALE, Feature.HAVE_LUNGS );
        EnumSet<Feature> birdFeautres = EnumSet.of( Feature.HAS_BACKBONE, Feature.HAS_FUR, Feature.HAVE_LUNGS );
        EnumSet<Feature> amphibianFeautres = EnumSet.of( Feature.HAS_BACKBONE, Feature.CHAMBERED_HEART, Feature.HAVE_LUNGS );
        EnumSet<Feature> invertebrateFeautres = EnumSet.of( Feature.IS_AMNIOTES, Feature.CHAMBERED_HEART );

        List<Animal> animals = new ArrayList<>();
        animals.add( new Animal(AnimalType.MAMMAL, "Poe", new ArrayList<>( mammalFeautres ), Gender.MALE));
        animals.add( new Animal(AnimalType.REPTILE, "Viper", new ArrayList<>( reptileFeautres ), Gender.FEMALE));
        animals.add( new Animal(AnimalType.REPTILE, "Master Croc", new ArrayList<>( reptileFeautres ), Gender.MALE));
        animals.add( new Animal(AnimalType.BIRD, "Crane", new ArrayList<>( birdFeautres ), Gender.MALE));
        animals.add( new Animal(AnimalType.BIRD, "Eagle", new ArrayList<>( birdFeautres ), Gender.FEMALE));
        animals.add( new Animal(AnimalType.MAMMAL, "Flying Rhino", new ArrayList<>( mammalFeautres ), Gender.MALE));
        animals.add( new Animal(AnimalType.MAMMAL, "Tigress", new ArrayList<>( mammalFeautres ), Gender.FEMALE));
        animals.add( new Animal(AnimalType.INSECT, "Mantis", new ArrayList<>( invertebrateFeautres ), Gender.MALE));
        animals.add( new Animal(AnimalType.AQUATIC, "Mugan", new ArrayList<>( aquaticFeautres ), Gender.MALE));
        animals.add( new Animal(AnimalType.AMPHIBIAN, "Kungfu Frog", new ArrayList<>( amphibianFeautres ), Gender.FEMALE));
        return animals;
    }
}
