package com.zhang.web.common;

import com.zhang.web.util.MessageUtils;

/*
 * @author sunny
 * @description: Custom exception 自定义异常
 * @date 2022/11/1 11:38
 */
public class BaseException extends RuntimeException {

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

  public BaseException(int code) {
    this.code = code;
    // this.msg = MessageUtils.getMessage(code);
  }

  public BaseException(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public BaseException(int code, String... params) {
    this.code = code;
    this.msg = MessageUtils.getMessage(code, params);
  }

  public BaseException(int code, Throwable e) {
    super(e);
    this.code = code;
    this.msg = MessageUtils.getMessage(code);
  }

  public BaseException(int code, Throwable e, String... params) {
    super(e);
    this.code = code;
    this.msg = MessageUtils.getMessage(code, params);
  }

  public BaseException(String msg) {
    super(msg);
    this.code = ErrorCode.UNAUTHORIZED;
    this.msg = msg;
  }

  public BaseException(String msg, Object data) {
    super(msg);
    this.code = ErrorCode.UNAUTHORIZED;
    this.msg = msg;
    this.data = data;
  }

  public BaseException(String msg, Throwable e) {
    super(msg, e);
    this.code = ErrorCode.INTERNAL_SERVER_ERROR;
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
