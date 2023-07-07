package com.contacts.dto.mapper;

import com.contacts.dto.request.PhoneRequestDto;
import com.contacts.dto.response.PhoneResponseDto;
import com.contacts.model.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {
    public Phone toModel(PhoneRequestDto dto) {
        Phone phone = new Phone();
        phone.setPhone(dto.getPhone());
        return phone;
    }

    public PhoneResponseDto toDto(Phone phone) {
        PhoneResponseDto dto = new PhoneResponseDto();
        dto.setId(phone.getId());
        dto.setPhone(phone.getPhone());
        return dto;
    }
}
