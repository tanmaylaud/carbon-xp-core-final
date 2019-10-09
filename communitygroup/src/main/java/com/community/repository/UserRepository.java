package com.community.repository;

import com.community.beans.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByAccountNumber(String accountNumber);
}
