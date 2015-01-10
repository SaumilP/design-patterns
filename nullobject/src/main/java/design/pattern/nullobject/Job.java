package design.pattern.nullobject;

/**
 * Abstract Class holding Job Details
 */
public abstract class Job {
    private double salary;

    protected Job(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public abstract boolean isNull();

    @Override public String toString() {
        return "Job{" +
               "salary=" + salary +
               '}';
    }
}
