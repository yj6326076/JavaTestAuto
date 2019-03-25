package com.yj.testdemo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author yangjun
 */
@Data
@Entity
public class ExecuteResult {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 执行结果
     */
    @Column
    private Boolean result;

    /**
     * 描述内容
     */
    @Column(length = 40)
    private String info;
}
