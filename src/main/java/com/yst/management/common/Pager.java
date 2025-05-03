package com.yst.management.common;

import lombok.Data;

import java.util.List;

@Data
public class Pager<T> {
    private Integer pageNum; //分页起始页
    private Integer pageSize; //每页记录数
    private List<T> data; //数据集合
    private Long total; //总页数
}
