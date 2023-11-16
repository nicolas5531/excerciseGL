package com.globallogic.ejerciciobci.controllers.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    @NotEmpty
    private Long number;
    private Integer cityCode;
    private String countryCode;
}
