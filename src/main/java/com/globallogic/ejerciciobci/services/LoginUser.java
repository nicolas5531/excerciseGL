package com.globallogic.ejerciciobci.services;

import com.globallogic.ejerciciobci.repositories.IUserRepository;
import com.globallogic.ejerciciobci.repositories.entities.User;
import com.globallogic.ejerciciobci.services.util.JWTUtil;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Builder
public class LoginUser {
    private final IUserRepository userRepository;
    private final JWTUtil jwtUtil;

    public User login(String token) {
        User user = getUser(token);

        user.setLastLogin(LocalDateTime.now());

        String newToken = jwtUtil.createTokenWithShortExpiration(user);
        user.setToken(newToken);

        return userRepository.save(user);
    }

    private User getUser(String token) {
        String userEmail = jwtUtil.verify(token);

        return userRepository.findByEmail(userEmail);
    }
}
