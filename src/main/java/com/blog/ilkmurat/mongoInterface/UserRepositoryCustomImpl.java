package com.blog.ilkmurat.mongoInterface;

import com.blog.ilkmurat.model.User;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User authenticateUser(User user) {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if (user.getUsername() != null && user.getPassword() != null) {
            criteria.add(Criteria.where("username").in(user.getUsername()));
            criteria.add(Criteria.where("password").in(user.getPassword()));
        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }

        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user, "user");
    }

    @Override
    public boolean isUserExist(User user) {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if (user.getUsername() != null) {
            criteria.add(Criteria.where("username").in(user.getUsername()));
        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }

        return mongoTemplate.findOne(query, User.class) != null;

    }

    @Override
    public void deleteUserById(String id) {
        User user = this.findByID(id) ;
        if (user != null) {
            mongoTemplate.remove(user);
        }
    }

    @Override
    public void updateUser(User user) {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if (user.getUsername() != null) {
            criteria.add(Criteria.where("username").in(user.getUsername()));
        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }

        User user1 = mongoTemplate.findOne(query, User.class);
        if (user1 != null) {
            Update update = new Update();
            update.set("mail", user.getUsername());
            update.set("name", user.getName());
            update.set("surname", user.getSurname());
            mongoTemplate.updateFirst(query, update, User.class);

        }

    }

    @Override
    public User findByID(String id) {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if (id != null) {
            criteria.add(Criteria.where("_id").in(id));
        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }

        return mongoTemplate.findOne(query, User.class);
    }
}
