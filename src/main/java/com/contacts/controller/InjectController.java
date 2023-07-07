package com.contacts.controller;

import com.contacts.model.Contact;
import com.contacts.model.Email;
import com.contacts.model.Phone;
import com.contacts.model.User;
import com.contacts.service.ContactService;
import com.contacts.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/inject")
public class InjectController {
    private final UserService userService;
    private final ContactService contactService;

    @GetMapping
    public String injection() {
        User user = new User();
        user.setName("Admin");
        user.setPassword("adminadmin");
        userService.create(user);

        Email email = new Email();
        email.setEmail("a@a.com");
        Email email2 = new Email();
        email2.setEmail("b@b.com");
        List<Email> emails = List.of(email, email2);

        Phone phone = new Phone();
        phone.setPhone("+380501234567");
        Phone phone2 = new Phone();
        phone2.setPhone("+0501234567");
        List<Phone> phones = List.of(phone, phone2);

        Contact contact = new Contact();
        contact.setName("John");
        contact.setEmails(emails);
        contact.setPhones(phones);
        contactService.create(user.getName(), contact);
        System.out.println(contact.getId());

        phone.setPhone("+388888888888");
        phone.setId(1L);
        phone2.setPhone("5555555555");
        phone2.setId(2L);
        email.setId(1L);
        email2.setId(2L);
        contact.setPhones(phones);
        contact.setId(1L);
        contactService.update(user.getName(), contact.getId(), contact);

        Phone phonex = new Phone();
        phonex.setPhone("+380501234567");
        List<Phone> phonesx = List.of(phonex);
        Email emailx = new Email();
        emailx.setEmail("a@a.com");
        List<Email> emailsx = List.of(emailx);
        Contact contact2 = new Contact();
        contact2.setName("Bill");
        contact2.setEmails(emailsx);
        contact2.setPhones(phonesx);
        contactService.create(user.getName(), contact2);

        contactService.delete(user.getName(), 2L);

        return "Injection success!";
    }
}
