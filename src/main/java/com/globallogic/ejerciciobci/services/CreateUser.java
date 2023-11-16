package com.globallogic.ejerciciobci.services;

import com.globallogic.ejerciciobci.controllers.dto.UserDTO;
import com.globallogic.ejerciciobci.controllers.exceptions.ExistentUserFoundException;
import com.globallogic.ejerciciobci.repositories.IUserRepository;
import com.globallogic.ejerciciobci.repositories.entities.User;
import com.globallogic.ejerciciobci.services.util.JWTUtil;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Builder
public class CreateUser {
    private final IUserRepository userRepository;
    private final JWTUtil jwtUtil;

    public User create(UserDTO userDTO) {
        checkExistentUser(userDTO.getEmail());

        User user = new User(userDTO);

        String jwt = jwtUtil.createTokenWithShortExpiration(user);
        user.setToken(jwt);

        return userRepository.save(user);
    }

    private void checkExistentUser(String email) {
        try {
            User existentUser = userRepository.findByEmail(email);
            if (existentUser != null)
                throw new ExistentUserFoundException();
        } catch (NoSuchElementException ignored) {
        }
    }
}
