package com.contacts.service.impl;

import com.contacts.exception.ContactsException;
import com.contacts.model.Phone;
import com.contacts.repository.PhoneRepository;
import com.contacts.service.PhoneService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;

    @Override
    public List<Phone> saveAll(List<Phone> phones) {
        return phoneRepository.saveAll(phones);
    }

    @Override
    public Phone update(Long id, Phone phone) {
        phone.setId(id);
        return phoneRepository.save(phone);
    }

    @Override
    public Phone findById(Long id) {
        return phoneRepository.findById(id)
                .orElseThrow(() -> new ContactsException("Can't find phone by id " + id));
    }

    @Override
    public void delete(Long id) {
        phoneRepository.deleteById(id);
    }
}
