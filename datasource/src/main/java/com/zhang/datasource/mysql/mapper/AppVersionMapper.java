package com.zhang.datasource.mysql.mapper;

import com.zhang.datasource.mysql.model.AppVersionDO;

import java.util.List;

/*
 * <p>
 * app version info
 * </p>
 */
public interface AppVersionMapper{

  /**
   * get by id
   *
   * @param id
   * @return
   */
  List<AppVersionDO> getById(Integer id);
}
