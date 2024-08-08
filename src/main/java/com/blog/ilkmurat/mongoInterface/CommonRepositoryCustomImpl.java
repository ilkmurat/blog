package com.blog.ilkmurat.mongoInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CommonRepositoryCustomImpl implements CommonRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CommonRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public <T> List<T> getListById(String id, String searchIdField, Class<T> clazz) {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if (id != null) {
            criteria.add(Criteria.where(searchIdField).in(id));
        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }
        return mongoTemplate.find(query, clazz);

    }
}
