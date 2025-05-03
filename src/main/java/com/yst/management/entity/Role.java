package com.yst.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Role {
    @TableId("id")
    private Integer id;
    private String name;
    private String permissionIds;  //外键
    private String description;
}
