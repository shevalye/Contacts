package com.contacts.repository;

import com.contacts.model.Contact;
import com.contacts.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> getAllByUser(User user);
}
