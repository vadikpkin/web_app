package com.epam.dao;

import com.epam.models.Person;

import java.util.Set;

public interface PersonDaoInterface {
    Person getPerson(int id);

    Set<Person> getAllPersons();

    boolean insertPerson(Person person);

    boolean updatePerson(Person person, int id);

    boolean deletePerson(int id);
}
