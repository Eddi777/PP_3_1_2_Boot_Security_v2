package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepositoryImpl;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepositoryImpl;
    public UserServiceImpl(UserRepositoryImpl userRepositoryImpl) {
        this.userRepositoryImpl = userRepositoryImpl;
    }
    @Override
    public User getUserByUsername(String username){
        return userRepositoryImpl.getUserByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userRepositoryImpl.saveUser(user);
    }
    @Override
    public void updateUser(User user) {
        userRepositoryImpl.updateUser(user);
    }
    @Override
    public User findById(Long id) {
        return userRepositoryImpl.getUserById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepositoryImpl.getUsersList();
    }

    @Override
    public void deleteById(Long id) {
        userRepositoryImpl.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

}
