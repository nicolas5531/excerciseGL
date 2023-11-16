package com.globallogic.ejerciciobci.controllers;

import com.globallogic.ejerciciobci.controllers.dto.LoginDTO;
import com.globallogic.ejerciciobci.controllers.dto.UserDTO;
import com.globallogic.ejerciciobci.repositories.entities.User;
import com.globallogic.ejerciciobci.services.CreateUser;
import com.globallogic.ejerciciobci.services.LoginUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    private final CreateUser createUser;
    private final LoginUser loginUser;

    public UserController(CreateUser createUser, LoginUser loginUser) {
        this.createUser = createUser;
        this.loginUser = loginUser;
    }

    @PostMapping("/sign-up")
    public User signUp(@RequestBody @Valid UserDTO userDTO) {
        return createUser.create(userDTO);
    }

    @PostMapping("/login")
    public User signUp(@RequestBody @Valid LoginDTO body) {
        String token = body.getToken();
        return loginUser.login(token);
    }
}
