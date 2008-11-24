package com.themangoproject.model;

public interface PersonDAO {

    public void addPerson(String name, String address, long phoneNumber, String email);

    public void updatePerson(Person person);

    public void Unnamed();
}

