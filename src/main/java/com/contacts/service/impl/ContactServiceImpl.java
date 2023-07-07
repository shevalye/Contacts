package com.contacts.service.impl;

import com.contacts.exception.ContactsException;
import com.contacts.model.Contact;
import com.contacts.model.Email;
import com.contacts.model.Phone;
import com.contacts.model.User;
import com.contacts.repository.ContactRepository;
import com.contacts.service.ContactService;
import com.contacts.service.UserService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final UserService userService;

    @Transactional
    @Override
    public Contact create(String name, Contact contact) {
        User user = userService.findByName(name);
        if (contact.getEmails() != null) {
            checkEmails(contact.getEmails());
        }
        if (contact.getPhones() != null) {
            checkPhoneNumbers(contact.getPhones());
        }
        contact.setUser(user);
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);
        user.setContacts(contacts);
        userService.create(user);
        return contact;
    }

    @Override
    public Contact update(String name, Long id, Contact contact) {
        contact.setId(id);
        if (contact.getEmails() != null) {
            checkEmails(contact.getEmails());
        }
        if (contact.getPhones() != null) {
            checkPhoneNumbers(contact.getPhones());
        }
        User user = userService.findByName(name);
        checkUser(user, contact);
        return contactRepository.save(contact);
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactsException("Can't find contact by id " + id));
    }

    @Transactional
    @Override
    public void delete(String name, Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactsException("Can't find contact by id " + id));
        User user = userService.findByName(name);
        checkUser(user, contact);
        List<Contact> contacts = user.getContacts();
        contacts.remove(contact);
        userService.create(user);
        contactRepository.deleteById(id);
    }

    @Override
    public List<Contact> getAllByUser(String userName) {
        User user = userService.findByName(userName);
        return contactRepository.getAllByUser(user);
    }

    private void checkUser(User user, Contact contact) {
        if (contact.getUser().getId() != user.getId()) {
            throw new ContactsException("This contact do not below to user " + user.getName());
        }
    }

    private void checkEmails(List<Email> emails) {
        for (Email email : emails) {
            if (!isValidEmail(email.getEmail())) {
                throw new ContactsException("Email: " + email.getEmail() + " is not valid!");
            }
        }
    }

    private void checkPhoneNumbers(List<Phone> phones) {
        for (Phone phone : phones) {
            if (!isValidPhoneNumber(phone.getPhone())) {
                throw new ContactsException("Phone number: " + phone.getPhone() + " is not valid!");
            }
        }
    }

    public boolean isValidEmail(String email) {
        return email.matches("^(.+)@(.+)$");
    }

    public boolean isValidPhoneNumber(String number) {
        return number.matches("^[0-9\\-\\+]{9,15}$");
    }
}
