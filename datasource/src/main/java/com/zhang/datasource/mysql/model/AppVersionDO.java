package com.zhang.datasource.mysql.model;

import java.time.LocalDateTime;

/*
 * <p>
 * app version info
 * </p>
 */
public class AppVersionDO {

  /** index. */
  private Integer id;

  /** version. */
  private String version;

  /** 1-android, 2-ios. */
  private Integer appType;

  /** force update or no:0-no, 1- yes. */
  private Integer forceOrNo;

  /** create time. */
  private LocalDateTime createTime;

  /** delete or no: 0-no, 1-yes. */
  private Integer deleted;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Integer getAppType() {
    return appType;
  }

  public void setAppType(Integer appType) {
    this.appType = appType;
  }

  public Integer getForceOrNo() {
    return forceOrNo;
  }

  public void setForceOrNo(Integer forceOrNo) {
    this.forceOrNo = forceOrNo;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }
}
