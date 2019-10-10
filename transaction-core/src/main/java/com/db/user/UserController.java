package com.db.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/mongo/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;




    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    public String create(@RequestBody User user) {


        user.setAccountNumber(String.valueOf(new Date().getTime()));
        userService.create(user);
        return "user is created.";
    }



    @GetMapping(value= "/getallUsers")
    public Collection<User> getAll() {
        return userService.findAll();
    }


    @GetMapping(value= "/getUserByAccountNumber/{account-number}")
    public Optional<User> getByAccountNumber(@PathVariable(value= "account-number") String accountNumber) {
        return userService.findByAccountNumber(accountNumber).stream().findFirst();
    }

    @GetMapping(value= "/getUserByEmailId/{email-Id}")
    public Optional<User> getUserByAccountNumber(@PathVariable(value= "email-Id") String emailId) {
        return userService.findUserByEmailId(emailId).stream().findFirst();
    }


}
