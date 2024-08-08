package com.blog.ilkmurat.repository;

import com.blog.ilkmurat.model.Comment;
import com.blog.ilkmurat.mongoInterface.CommonRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String>, CommonRepositoryCustom {

}
