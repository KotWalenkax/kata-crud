package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDao {

    void save(User user);
    void update(User user);
    void deleteById(long id);
    User findById(long id);
    List<User> findAll();

}
