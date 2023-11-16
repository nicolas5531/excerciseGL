package com.globallogic.ejerciciobci.controllers.exceptions;

import lombok.Getter;

import java.time.Instant;

@Getter
public class Error {
  private final Long timestamp;
  private final int codigo;
  private final String detail;

  public Error(int codigo, String detail) {
    this.timestamp = Instant.now().getEpochSecond();
    this.codigo = codigo;
    this.detail = detail;
  }
}
