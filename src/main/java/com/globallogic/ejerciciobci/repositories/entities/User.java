package com.globallogic.ejerciciobci.repositories.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.globallogic.ejerciciobci.controllers.dto.PhoneDTO;
import com.globallogic.ejerciciobci.controllers.dto.UserDTO;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private String email;
    private String password;
    @JsonIgnoreProperties("user")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Phone> phones;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    @Column(length = 2048)
    private String token;
    private Boolean isActive;

    public User(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.phones = createPhones(userDTO.getPhones());
        this.created = LocalDateTime.now();
        this.isActive = true;
    }

    private Set<Phone> createPhones(Set<PhoneDTO> phoneDTOS) {
        return phoneDTOS.stream()
                .map(this::createPhone)
                .collect(Collectors.toSet());
    }

    private Phone createPhone(PhoneDTO phoneDTO) {
        Phone phone = new Phone(phoneDTO);
        phone.setUser(this);
        return phone;
    }
}
