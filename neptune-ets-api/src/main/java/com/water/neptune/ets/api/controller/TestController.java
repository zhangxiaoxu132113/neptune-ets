package com.water.neptune.ets.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.water.neptune.ets.model.po.TestPO;
import com.water.neptune.ets.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Zhang Miaojie
 * @date 2020/2/11
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping(value = "/test")
    public void test() {
        TestPO one = testService.getOne(new LambdaQueryWrapper<TestPO>().eq(TestPO::getId, 1L));
        System.out.println(one.getName());
    }
}
