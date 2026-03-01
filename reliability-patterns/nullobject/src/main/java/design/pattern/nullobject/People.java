package design.pattern.nullobject;

import java.util.ArrayList;
import java.util.List;

/**
 * Container class for {@link Person}s
 */
public class People {
    private List<Person> persons;

    public People() {
        persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person){
        persons.add( person );
    }

    public List<Person> findPeopleWithJobs(){
        List<Person> retList = new ArrayList<>();
        for (Person person : persons) {
            if( !person.getJob().isNull() ){
                retList.add( person );
            }
        }
        return retList;
    }
}
