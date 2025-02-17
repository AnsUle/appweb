package sprintboot.appweb.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprintboot.appweb.model.Person;
import sprintboot.appweb.repository.PersonRepository;

@Data
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person getPerson(Integer id) {
        return personRepository.getPerson(id);
    }

    public Iterable<Person> getAllPersons() {
        return personRepository.getPersons();
    }

    public void deletePerson(final Integer id) {
        personRepository.deletePerson(id);
    }

    public Person savePerson(final Person person) {
        Person saved;
        person.setLastName(person.getLastName().toUpperCase());
        if(person.getId() == null){
            saved = personRepository.createPerson(person);
        } else {
            saved = personRepository.updatePerson(person);
        }
        return saved;
    }
}
