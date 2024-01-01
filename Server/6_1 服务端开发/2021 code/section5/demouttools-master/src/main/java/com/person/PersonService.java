package com.person;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public boolean update(int id, String name) {
        Person person = personRepository.getPerson(id);

        if (person == null) {
            return false;
        }

        Person personUpdate = new Person(person.getId(), name);
        return personRepository.update(personUpdate);
    }
}