package com.kzn.itis.db.repositories;

import com.kzn.itis.db.model.User;

public interface UserRepository {

    User addUser(User event);

    long getCount();
}
