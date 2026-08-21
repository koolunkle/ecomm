package com.example.ecomm.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CardAlreadyExistsException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final String errMsgKey;
  private final String errorCode;

  public CardAlreadyExistsException(final String message) {
    super(message);
    this.errMsgKey = ErrorCode.CARD_ALREADY_EXISTS.getErrMsgKey();
    this.errorCode = ErrorCode.CARD_ALREADY_EXISTS.getErrCode();
  }
}
