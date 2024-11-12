package com.zhang.web.controller;

import com.zhang.datasource.mysql.mapper.AppVersionMapper;
import com.zhang.datasource.mysql.model.AppVersionDO;
import com.zhang.web.config.CustomConfig;
import com.zhang.web.util.MessageUtils;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Locale;

/*
 *
 * @author:     zhangtopsun@foxmail.com
 * @date:    2024/6/29 22:43
 * @version:    1.0
 */
@RestController
@RequestMapping("datasource/")
public class DataSourceController {
    @Resource
    private CustomConfig customConfig;

    @Resource private AppVersionMapper appVersionMapper;

    @Resource private MessageSource messageSource;

    @GetMapping("get/yml")
    public void getYml() {
        AppVersionDO appVersionDO = appVersionMapper.getById(1);
        System.out.println(appVersionDO.getVersion());
    }

}
