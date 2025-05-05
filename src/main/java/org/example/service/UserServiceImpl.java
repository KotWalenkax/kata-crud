package org.example.service;

import org.example.dao.UserDao;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(Long id, User updatedUser) {
        User user = findById(id);
        user.setName(updatedUser.getName());
        user.setAge(updatedUser.getAge());
        userDao.update(user);
    }

    @Override
    public void deleteById(long id) {
        User user = findById(id);
        userDao.deleteById(user.getId());
    }

    @Override
    public User findById(long id) throws UserNotFoundException {
        return userDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
