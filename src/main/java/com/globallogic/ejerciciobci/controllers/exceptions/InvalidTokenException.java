package com.globallogic.ejerciciobci.controllers.exceptions;

public class InvalidTokenException extends RuntimeException {
  public InvalidTokenException(Exception exception) {
    super("Token invalido.", exception);
  }
}
