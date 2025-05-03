package com.yst.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Permission {
    @TableId("id")
    private Integer id;
    private String name;
    private Integer pid;
    private Integer level;
}
