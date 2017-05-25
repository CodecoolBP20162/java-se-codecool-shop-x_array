package com.codecool.shop.dao;

import com.codecool.shop.model.User;

/**
 * Created by adam_kovacs on 25.05.17.
 */
public interface UserDao {

    void add(User user);

    void remove(User user);

    Object find(int id);

}
