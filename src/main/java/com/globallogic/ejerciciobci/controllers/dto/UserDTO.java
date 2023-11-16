package com.globallogic.ejerciciobci.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    @Email(message = "El formato del email es incorrecto.")
    private String email;
    @Pattern(regexp = "^(?=.{8,12}$)[a-z0-9]*[A-Z][a-z0-9]*$",
            message = "El formato de la contraseña es incorrecto.")
    private String password;
    private Set<PhoneDTO> phones;
}
