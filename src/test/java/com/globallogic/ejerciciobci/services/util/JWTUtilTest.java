package com.globallogic.ejerciciobci.services.util;

import com.globallogic.ejerciciobci.controllers.exceptions.InvalidTokenException;
import com.globallogic.ejerciciobci.repositories.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JWTUtilTest {

    @InjectMocks
    private JWTUtil jwtUtil;

    @Mock
    private User mockUser;

    @Test
    void testSignAndVerifyToken() {
        when(mockUser.getEmail()).thenReturn("test@example.com");

        String token = jwtUtil.createTokenWithShortExpiration(mockUser);
        String subject = jwtUtil.verify(token);

        assertEquals("test@example.com", subject);
    }

    @Test
    void testVerifyInvalidToken() {
        assertThrows(InvalidTokenException.class, () -> jwtUtil.verify("invalidToken"));
    }

    @Test
    void testGetSecret() {
        jwtUtil.setSecret(null);
        assertEquals("test", jwtUtil.getSecret());

        jwtUtil.setSecret("customSecret");
        assertEquals("customSecret", jwtUtil.getSecret());
    }

}
