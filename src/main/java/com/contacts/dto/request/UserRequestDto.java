package com.contacts.dto.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotNull(message = "Name cannot be empty")
    @Size(min = 5, max = 20, message
            = "Name must be between 5 and 20 characters")
    private String name;
    @NotNull(message = "Password cannot be empty")
    @Size(min = 6, max = 20, message
            = "Password must be between 6 and 20 characters")
    private String password;
    private String role;
    private List<Long> contactIds;
}
