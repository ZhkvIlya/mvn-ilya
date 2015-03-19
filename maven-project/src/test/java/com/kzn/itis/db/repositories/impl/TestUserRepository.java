package com.kzn.itis.db.repositories.impl;

import com.kzn.itis.db.model.User;
import com.kzn.itis.db.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class TestUserRepository {

    @Test
    public void testCreate() {
        UserRepository userRepository = new UserRepositoryImpl();

        long count = userRepository.getCount();

        User user = new User();
        user.setAge(25);
        user.setFirstname("James");
        user.setLastname("Bond");

        userRepository.addUser(user);

        long newCount = userRepository.getCount();

        Assert.assertEquals(count + 1, newCount);
    }
}
