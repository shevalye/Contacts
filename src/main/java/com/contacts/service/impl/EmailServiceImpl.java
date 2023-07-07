package com.contacts.service.impl;

import com.contacts.exception.ContactsException;
import com.contacts.model.Email;
import com.contacts.repository.EmailRepository;
import com.contacts.service.EmailService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;

    @Override
    public List<Email> saveAll(List<Email> emails) {
        return emailRepository.saveAll(emails);
    }

    @Override
    public Email update(Long id, Email email) {
        email.setId(id);
        return emailRepository.save(email);
    }

    @Override
    public Email findById(Long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new ContactsException("Can't find email by id " + id));
    }

    @Override
    public void delete(Long id) {
        emailRepository.deleteById(id);
    }
}
