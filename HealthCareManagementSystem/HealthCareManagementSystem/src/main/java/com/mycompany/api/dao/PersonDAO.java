/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao;

/**
 *
 * @author User
 */
import com.mycompany.api.model.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    // List to store mock person data
    private static List<Person> persons = new ArrayList<>();

    // Add some mock data
    static {
        persons.add(new Person(1, "John Doe", "john@example.com", "123 Main St"));
        persons.add(new Person(2, "Jane Smith", "jane@example.com", "456 Elm St"));
    }

    // Method to get all persons
    public List<Person> getAllPersons() {
        return persons;
    }

    // Method to get a person by ID
    public Person getPersonById(int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null; // Return null if person not found
    }

    // Method to add a new person
    public void addPerson(Person person) {
        persons.add(person);
    }

    // Method to update an existing person
    public void updatePerson(int id, Person updatedPerson) {
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getId() == id) {
                // Update properties of the existing person with the new values
                person.setName(updatedPerson.getName());
                person.setContactInfo(updatedPerson.getContactInfo());
                person.setAddress(updatedPerson.getAddress());
                return; // Exit loop once person is updated
            }
        }
    }

    // Method to delete a person by ID
    public void deletePerson(int id) {
        persons.removeIf(person -> person.getId() == id);
    }
}





