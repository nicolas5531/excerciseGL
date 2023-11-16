package com.globallogic.ejerciciobci.repositories;

import com.globallogic.ejerciciobci.repositories.entities.User;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class UserRepositorySQL implements IUserRepository {
    private final UserRepositoryJPA userRepositoryJPA;

    public UserRepositorySQL(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @Override
    public User save(User user) {
        user = userRepositoryJPA.save(user);

        return user;
    }

    @Override
    public User findById(String id) throws NoSuchElementException {
        return userRepositoryJPA.findById(id)
                .orElseThrow(this::getUserNotFoundException);
    }

    @Override
    public User findByEmail(String email) throws NoSuchElementException {
        return userRepositoryJPA.findByEmail(email)
                .orElseThrow(this::getUserNotFoundException);
    }

    private NoSuchElementException getUserNotFoundException() {
        return new NoSuchElementException("User not found.");
    }
}
