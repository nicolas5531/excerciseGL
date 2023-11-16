package com.globallogic.ejerciciobci.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@Setter
@Getter
public class ExceptionDTO {
    private final List<Error> errors;
}
