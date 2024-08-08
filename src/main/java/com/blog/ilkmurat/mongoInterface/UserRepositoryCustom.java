package com.blog.ilkmurat.mongoInterface;

import com.blog.ilkmurat.model.User;

import java.util.List;

public interface UserRepositoryCustom {

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(String id);

    boolean isUserExist(User user);

    User authenticateUser(User user);

    User findByID(String id);
}
