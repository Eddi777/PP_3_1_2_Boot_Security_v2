package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
//    private final UserRepositoryCRUD userRepositoryCRUD;
    public UserService(UserRepository userRepository) {
//        this.userRepositoryCRUD = userRepositoryCRUD;
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
//        return userRepositoryCRUD.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.saveUser(user);
//        userRepositoryCRUD.save(user);
    }
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
    public User findById(Long id) {
        return userRepository.getUserById(id);
//        return userRepositoryCRUD.getOne(id);
    }

    public List<User> findAll() {
        return userRepository.getUsersList();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
//        userRepositoryCRUD.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

}
