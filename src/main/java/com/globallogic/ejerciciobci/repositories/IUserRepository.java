package com.globallogic.ejerciciobci.repositories;

import com.globallogic.ejerciciobci.repositories.entities.User;

import java.util.NoSuchElementException;

public interface IUserRepository {
  User save(User user);

  User findById(String id) throws NoSuchElementException;

  User findByEmail(String email) throws NoSuchElementException;
}
