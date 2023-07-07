package com.contacts.service.impl;

import com.contacts.exception.ContactsException;
import com.contacts.model.User;
import com.contacts.repository.UserRepository;
import com.contacts.service.UserService;
import java.util.List;
import java.util.Optional;
import javax.naming.AuthenticationException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ContactsException("Can't find user by id " + id));
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new ContactsException("can't find user by name " + name));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User login(String name, String password) throws AuthenticationException {
        Optional<User> user = userRepository.findByName(name);
        String encodedPassword = passwordEncoder.encode(password);
        if (user.isEmpty() || user.get().getPassword().equals(encodedPassword)) {
            throw new AuthenticationException("Incorrect username or password!!!");
        }
        return user.orElseThrow(() ->
                new RuntimeException("Can't find user with user name " + name));
    }
}
