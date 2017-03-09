package facade;

import facadeApi.PersonRepository;
import jpa.Person;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Created by gadzik on 09.03.17.
 */
@Stateless
public class PersonFacade extends AbstractFacade implements PersonRepository {


    @Override
    public void addPerson(Person person) {
        entityManager.persist(person);
    }

    @Override
    public void deletePerson(long id) {
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
    }

    @Override
    public List<Person> getAllPersons() {
        TypedQuery query = entityManager.createQuery("SELECT X FROM Person X", Person.class);
        return query.getResultList();
    }

    @Override
    public void updatePerson(Person person) {
        Person currentPerson = entityManager.find(Person.class, person.getId());
        currentPerson.setName(person.getName());
        currentPerson.setLname(person.getLname());
        currentPerson.setDob(person.getDob());
        entityManager.merge(currentPerson);
    }

    @Override
    public Person getParticularPerson(long id) {
        return entityManager.find(Person.class, id);
    }
}
