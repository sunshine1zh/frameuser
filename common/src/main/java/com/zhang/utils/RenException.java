package com.zhang.utils;

/*
 * @author sunny
 * @description: Custom exception 自定义异常
 * @date 2022/11/1 11:38
 */
public class RenException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private int code;
  private String msg;
  private Object data;

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public RenException(int code) {
    this.code = code;
    // this.msg = MessageUtils.getMessage(code);
  }

  public RenException(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public RenException(int code, String... params) {
    this.code = code;
//    this.msg = MessageUtils.getMessage(code, params);
  }

  public RenException(int code, Throwable e) {
    super(e);
    this.code = code;
//    this.msg = MessageUtils.getMessage(code);
  }

  public RenException(int code, Throwable e, String... params) {
    super(e);
    this.code = code;
//    this.msg = MessageUtils.getMessage(code, params);
  }

  public RenException(String msg) {
    super(msg);
//    this.code = ErrorCode.UNAUTHORIZED;
    this.msg = msg;
  }

  public RenException(String msg, Object data) {
    super(msg);
//    this.code = ErrorCode.UNAUTHORIZED;
    this.msg = msg;
    this.data = data;
  }

  public RenException(String msg, Throwable e) {
    super(msg, e);
//    this.code = ErrorCode.INTERNAL_SERVER_ERROR;
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
