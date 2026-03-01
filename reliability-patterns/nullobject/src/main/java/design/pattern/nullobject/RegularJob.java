package design.pattern.nullobject;

/**
 * Regular Job
 */
public class RegularJob extends Job {
    private String name;

    protected RegularJob(String name, double salary) {
        super(salary);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public boolean isNull() {
        return false;
    }

    @Override public String toString() {
        return "RegularJob{" +
               "salary=" + getSalary() +
               "name='" + name + '\'' +
               '}';
    }
}
