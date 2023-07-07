package com.contacts.security;

import com.contacts.dto.mapper.UserMapper;
import com.contacts.dto.request.UserRequestDto;
import com.contacts.dto.response.UserResponseDto;
import com.contacts.exception.ContactsException;
import com.contacts.model.User;
import com.contacts.repository.UserRepository;
import com.contacts.security.jwt.JwtService;
import com.contacts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserMapper userMapper;
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationResponse login(UserResponseDto userResponseDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userResponseDto.getName(),
                        userResponseDto.getPassword()
                )
        );
        var user = userRepository.findByName(userResponseDto.getName())
                .orElseThrow(() ->
                        new ContactsException("Can't find user by name "
                                + userResponseDto.getName()));
        var userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        var jwtToken = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse registration(UserRequestDto userRequestDto) {
        User user = userMapper.toModel(userRequestDto);
        userService.create(user);
        var userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(userRequestDto.getName())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .roles("USER")
                .build();
        var jwtToken = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
