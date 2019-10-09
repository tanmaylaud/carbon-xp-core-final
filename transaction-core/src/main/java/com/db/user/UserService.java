package com.db.user;

import java.util.List;

public interface UserService {

    public void create(User user);

    public void update(User user);

    public void delete(User user);

    public void deleteAll();

    public User find(User user);

    public List<User> findByAccountNumber(String accountNumber);


    public List <User> findAll();
}
