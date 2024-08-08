package com.blog.ilkmurat.mongoInterface;


import java.util.List;

public interface CommonRepositoryCustom {

    public <T> List<T> getListById(String id, String searchIdField, Class<T> clazz);
}
