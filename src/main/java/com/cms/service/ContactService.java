package com.cms.service;

import org.springframework.stereotype.Service;
import com.cms.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    // In-memory list to store contacts
    private List<Contact> contacts = new ArrayList<>();

    // Constructor to initialize some sample data
    public ContactService() {
        contacts.add(new Contact(1L, "John Doe", "1234567890", "john@example.com", "123 Main St"));
        contacts.add(new Contact(2L, "Jane Smith", "0987654321", "jane@example.com", "456 Elm St"));
    }

    // Get all contacts
    public List<Contact> getAllContacts() {
        return contacts;
    }

    // Get a contact by ID
    public Contact getContactById(Long id) {
        Optional<Contact> contact = contacts.stream().filter(c -> c.getId().equals(id)).findFirst();
        return contact.orElse(null);
    }

    // Save or update a contact
    public Contact saveContact(Contact contact) {
        if (contact.getId() == null) {
            // Create a new contact (assign an ID)
            contact.setId((long) (contacts.size() + 1));
            contacts.add(contact);
        } else {
            // Update existing contact
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getId().equals(contact.getId())) {
                    contacts.set(i, contact);
                    break;
                }
            }
        }
        return contact;
    }

    // Delete a contact by ID
    public void deleteContact(Long id) {
        contacts.removeIf(contact -> contact.getId().equals(id));
    }

    // Search contacts by keyword
    public List<Contact> searchContacts(String keyword) {
        return contacts.stream()
                .filter(contact -> contact.getName().contains(keyword) || contact.getPhone().contains(keyword))
                .toList();
    }
}
