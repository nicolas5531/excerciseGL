package com.globallogic.ejerciciobci.repositories;

import com.globallogic.ejerciciobci.repositories.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJPA extends JpaRepository<User, String> {
  Optional<User> findByEmail(String email);
}
