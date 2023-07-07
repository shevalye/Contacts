package com.contacts.security;

import com.contacts.exception.ContactsException;
import com.contacts.model.User;
import com.contacts.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(userName);
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new ContactsException("Can't find user: " + userName));
    }
}
