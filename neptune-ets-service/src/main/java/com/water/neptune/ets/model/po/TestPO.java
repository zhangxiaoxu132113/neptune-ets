package com.water.neptune.ets.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Zhang Miaojie
 * @date 2020/2/10
 */
@Data
@ToString
@TableName("test")
public class TestPO implements Serializable {
    private static final long serialVersionUID = 3369139247652969989L;
    private Long id;
    private String name;
}
