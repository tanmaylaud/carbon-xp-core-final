package com.db.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl  implements UserService {


    @Autowired
    UserRepository userRepository;


    @Override

    public void create(User user) {

        User user1=userRepository.insert(user);
        System.out.println("user created---"+user1);

    }

    @Override
    public void update(User user) {
        User u1 = userRepository.save(user);
        System.out.println("Updated:- " + u1);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
        System.out.println("Deleted:- " + user.getAccountNumber());

    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();

    }

    @Override
    public User find(User user) {
        return null;
    }

    @Override
    public List<User> findByAccountNumber(String accountNumber) {
        return userRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
