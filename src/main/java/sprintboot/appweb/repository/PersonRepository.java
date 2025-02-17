package sprintboot.appweb.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sprintboot.appweb.config.CustomProperties;
import lombok.extern.slf4j.Slf4j;
import sprintboot.appweb.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonRepository {

    @Autowired
    private CustomProperties customProperties;

    public Iterable<Person> getPersons() {

        String baseURL = customProperties.getApiUrl();
        String getPersonURL = baseURL + "/persons";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Person>> response = restTemplate.exchange(
                getPersonURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}

        );
        log.debug("getPersons call" + response.getStatusCode());
        return response.getBody();
    }

    public Person getPerson(Integer id) {
        String baseApiUrl = customProperties.getApiUrl();
        String getPersonURL = baseApiUrl + "/person/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Person> response= restTemplate.exchange(
                getPersonURL,
                HttpMethod.GET,
                null,
                Person.class
        );
        log.debug("Get Person call" + response.getStatusCode());
        return response.getBody();
    }
    public Person createPerson(Person person) {
        String baseApiUrl = customProperties.getApiUrl();
        String getPersonURL = baseApiUrl + "/person";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Person> request = new HttpEntity<>(person);
        ResponseEntity<Person> response = restTemplate.exchange(
                getPersonURL,
                HttpMethod.POST,
                request,
                Person.class
        );
        log.debug("Create Person call" + response.getStatusCode());
        return response.getBody();
    }
    public Person updatePerson (Person person) {
        String baseApiUrl = customProperties.getApiUrl();
        String getPersonURL = baseApiUrl + "/person/" + person.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Person> request = new HttpEntity<>(person);
        ResponseEntity<Person> response = restTemplate.exchange(
                getPersonURL,
                HttpMethod.PUT,
                request,
                Person.class
        );
        log.debug("Update Person call" + response.getStatusCode());
        return response.getBody();
    }
    public void deletePerson (Integer id) {
        String baseApiUrl = customProperties.getApiUrl();
        String getPersonURL = baseApiUrl + "/person/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                getPersonURL,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        log.debug("Delete Person call" + response.getStatusCode());
    }
}
