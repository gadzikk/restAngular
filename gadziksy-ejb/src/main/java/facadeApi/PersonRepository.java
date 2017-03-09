package facadeApi;

import jpa.Person;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by gadzik on 09.03.17.
 */
@Local
public interface PersonRepository {
    void addPerson(Person person);
    void deletePerson(long id);
    List<Person> getAllPersons();
    void updatePerson(Person person);
    Person getParticularPerson(long id);
}
