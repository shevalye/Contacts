package com.contacts.dto.mapper;

import com.contacts.dto.request.UserRequestDto;
import com.contacts.dto.response.UserResponseDto;
import com.contacts.model.Contact;
import com.contacts.model.User;
import com.contacts.service.ContactService;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    private final ContactService contactService;

    public User toModel(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        if (dto.getContactIds() != null) {
            user.setContacts(dto.getContactIds()
                    .stream()
                    .map(contactService::findById)
                    .collect(Collectors.toList()));
        }
        return user;
    }

    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        if (user.getContacts() != null) {
            dto.setContactIds(user.getContacts()
                    .stream()
                    .map(Contact::getId)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
