package com.globallogic.ejerciciobci.repositories.entities;

import com.globallogic.ejerciciobci.controllers.dto.PhoneDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private Long number;
    private Integer cityCode;
    private String countryCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Phone() {
    }

    public Phone(PhoneDTO phoneDTO) {
        this.number = phoneDTO.getNumber();
        this.cityCode = phoneDTO.getCityCode();
        this.countryCode = phoneDTO.getCountryCode();
    }
}
