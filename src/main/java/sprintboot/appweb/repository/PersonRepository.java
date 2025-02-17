package repository;

import config.CustomConfig;
import lombok.extern.slf4j.Slf4j;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonRepository {

    @Autowired
    private CustomConfig customConfig;

    public Iterable<Person> getPersons() {

        String url = customConfig.getUrl();
        return null;
    }

}
