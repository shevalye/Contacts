package com.contacts.dto.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ContactRequestDto {
    @NotNull(message = "Name cannot be empty")
    @Size(min = 1, max = 20, message
            = "Name must be between 5 and 20 characters")
    private String name;
    private List<Long> emailIds;
    private List<Long> phoneIds;
}
