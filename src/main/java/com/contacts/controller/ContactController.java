package com.contacts.controller;

import com.contacts.dto.mapper.ContactMapper;
import com.contacts.dto.request.ContactRequestDto;
import com.contacts.dto.response.ContactResponseDto;
import com.contacts.model.Contact;
import com.contacts.security.TokenUtil;
import com.contacts.service.ContactService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/contacts")
public class ContactController {
    private final ContactMapper contactMapper;
    private final ContactService contactService;
    private final TokenUtil tokenUtil;

    @Tag(name = "Create Contact", description = "Create new contact")
    @PostMapping
    public ContactResponseDto create(@RequestBody ContactRequestDto contactRequestDto,
                                     @RequestHeader HttpHeaders header) {
        Contact contact = contactMapper.toModel(contactRequestDto);
        return contactMapper.toDto(contactService
                .create(tokenUtil.getUserName(header), contact));
    }

    @Tag(name = "Update Contact", description = "Update contact")
    @PutMapping()
    public ContactResponseDto update(@RequestBody ContactRequestDto contactRequestDto,
                                     @RequestParam Long contactId,
                                     @RequestHeader HttpHeaders header) {
        Contact contact = contactMapper.toModel(contactRequestDto);
        return contactMapper.toDto(contactService
                .update(tokenUtil.getUserName(header), contactId, contact));
    }

    @Tag(name = "Delete Contact", description = "Delete contact")
    @DeleteMapping
    public void delete(@RequestParam Long id, @RequestHeader HttpHeaders header) {
        contactService.delete(tokenUtil.getUserName(header), id);
    }

    @Tag(name = "Get contacts", description = "Get all contacts related to user")
    @GetMapping
    public List<ContactResponseDto> getAllUserContacts(@RequestHeader HttpHeaders header) {
        return contactService.getAllByUser(tokenUtil.getUserName(header))
                .stream()
                .map(contactMapper::toDto)
                .collect(Collectors.toList());
    }
}
