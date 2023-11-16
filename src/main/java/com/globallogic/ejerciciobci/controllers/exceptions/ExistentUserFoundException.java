package com.globallogic.ejerciciobci.controllers.exceptions;

public class ExistentUserFoundException extends RuntimeException{
  public ExistentUserFoundException() {
    super("Usuario ya existente.");
  }
}
