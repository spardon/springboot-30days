package com.spardon.mybatis.dao;

import com.spardon.mybatis.entity.User;

import java.util.List;

public interface UserDao {
    public void insert(User user);

    public User findUserById(int userId);

    public List<User> findAllUser();

}
