package com.contacts.dto.mapper;

import com.contacts.dto.request.ContactRequestDto;
import com.contacts.dto.response.ContactResponseDto;
import com.contacts.model.Contact;
import com.contacts.model.Email;
import com.contacts.model.Phone;
import com.contacts.service.EmailService;
import com.contacts.service.PhoneService;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContactMapper {
    private final EmailService emailService;
    private final PhoneService phoneService;

    public Contact toModel(ContactRequestDto dto) {
        Contact contact = new Contact();
        contact.setName(dto.getName());
        contact.setEmails(dto.getEmailIds()
                .stream()
                .map(emailService::findById)
                .collect(Collectors.toList()));
        contact.setPhones(dto.getPhoneIds()
                .stream()
                .map(phoneService::findById)
                .collect(Collectors.toList()));
        return contact;
    }

    public ContactResponseDto toDto(Contact contact) {
        ContactResponseDto dto = new ContactResponseDto();
        dto.setId(contact.getId());
        dto.setName(contact.getName());
        dto.setEmailIds(contact.getEmails()
                .stream()
                .map(Email::getId)
                .collect(Collectors.toList()));
        dto.setPhoneIds(contact.getPhones()
                .stream()
                .map(Phone::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
