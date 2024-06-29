package com.zhang.web.controller;

import com.zhang.web.config.CustomConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @GetMapping("get/yml")
    public void getYml() {
        System.out.println(customConfig.getUsername());
    }
}
