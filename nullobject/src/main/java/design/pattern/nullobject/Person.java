package design.pattern.nullobject;

/**
 * Person class containing normal details
 */
public class Person {
    private String name;
    private Job job;

    public Person(String name, Job job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override public String toString() {
        return "Person{" +
               "name='" + name + '\'' +
               ", job=" + job +
               '}';
    }
}
