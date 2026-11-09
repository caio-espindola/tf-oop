package br.pucrs;

public class NotEnoughGoldException extends RuntimeException {
  public NotEnoughGoldException(String message) {
    super(message);
  }
}
