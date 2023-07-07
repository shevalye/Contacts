package com.contacts.service;

import com.contacts.model.Contact;
import java.util.List;

public interface ContactService {
    Contact create(String name, Contact contact);

    Contact update(String name, Long id, Contact contact);

    Contact findById(Long id);

    void delete(String name, Long id);

    List<Contact> getAllByUser(String userName);
}
