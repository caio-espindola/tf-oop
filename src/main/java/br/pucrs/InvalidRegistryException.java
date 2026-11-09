package br.pucrs;

public class InvalidRegistryException extends RuntimeException {
  public InvalidRegistryException(String message) {
    super(message);
  }
}
