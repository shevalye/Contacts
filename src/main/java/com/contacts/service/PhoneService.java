package com.contacts.service;

import com.contacts.model.Phone;
import java.util.List;

public interface PhoneService {
    List<Phone> saveAll(List<Phone> phones);

    Phone update(Long id, Phone phone);

    Phone findById(Long id);

    void delete(Long id);
}
