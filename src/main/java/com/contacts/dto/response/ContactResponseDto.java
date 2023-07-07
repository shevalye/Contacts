package com.contacts.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class ContactResponseDto {
    private Long id;
    private String name;
    private List<Long> emailIds;
    private List<Long> phoneIds;
}
