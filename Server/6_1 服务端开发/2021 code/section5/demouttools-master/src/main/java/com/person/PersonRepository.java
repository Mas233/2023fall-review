package com.person;

public interface PersonRepository {
    Person getPerson(int id);

    boolean update(Person person);
}