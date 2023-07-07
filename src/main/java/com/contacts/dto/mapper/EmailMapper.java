package com.contacts.dto.mapper;

import com.contacts.dto.request.EmailRequestDto;
import com.contacts.dto.response.EmailResponseDto;
import com.contacts.model.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {
    public Email toModel(EmailRequestDto dto) {
        Email email = new Email();
        email.setEmail(dto.getEmail());
        return email;
    }

    public EmailResponseDto toDto(Email email) {
        EmailResponseDto dto = new EmailResponseDto();
        dto.setId(email.getId());
        dto.setEmail(email.getEmail());
        return dto;
    }
}
