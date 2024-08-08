package com.blog.ilkmurat.repository;

import com.blog.ilkmurat.model.User;
import com.blog.ilkmurat.mongoInterface.UserRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> ,UserRepositoryCustom {

}
