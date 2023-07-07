package com.contacts.service;

import com.contacts.model.Email;
import java.util.List;

public interface EmailService {
    List<Email> saveAll(List<Email> emails);

    Email update(Long id, Email email);

    Email findById(Long id);

    void delete(Long id);
}
