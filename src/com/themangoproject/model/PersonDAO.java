package com.themangoproject.model;

import java.util.List;

public interface PersonDAO {

    public void addPerson(Person person) throws PersonExistsException;

    public void updatePerson(Person person);

    public void populatePerson(Person person);

    public List<Person> getAllPersons();

}

