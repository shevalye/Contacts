package com.contacts.service;

import com.contacts.model.User;
import java.util.List;
import javax.naming.AuthenticationException;

public interface UserService {
    User create(User user);

    User findById(Long id);

    User findByName(String name);

    List<User> findAll();

    User login(String email, String password) throws AuthenticationException;
}
