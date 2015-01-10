package design.pattern.nullobject;

/**
 * NullObject Client
 */
public class App {
    public static void main(String[] args){
        Job job = new RegularJob("Teacher",3000);
        Person johnDoe = new Person("John Doe", job);
        Person janeDoe = new Person("Jane Doe", new NullJob());
        People people = new People();

        people.addPerson( johnDoe );
        people.addPerson(janeDoe);

        for (Person person : people.findPeopleWithJobs()) {
            System.out.println(String.format("%s earns %.2f.", person.getName(), person.getJob().getSalary()) );
        }
    }
}
