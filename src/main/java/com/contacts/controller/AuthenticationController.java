package com.contacts.controller;

import com.contacts.dto.request.UserRequestDto;
import com.contacts.dto.response.UserResponseDto;
import com.contacts.security.AuthenticationResponse;
import com.contacts.security.AuthenticationService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserResponseDto userResponseDto) {
        return ResponseEntity.ok((authenticationService.login(userResponseDto)));
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> registration(
            @Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok((authenticationService.registration(userRequestDto)));
    }
}
