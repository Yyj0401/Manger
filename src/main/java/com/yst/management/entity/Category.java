package com.yst.management.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Category {
    @TableId("cat_id")
    private Integer catId;
    private String catName;
    private Integer catPid;
    private Integer catLevel;
    private Boolean isDelete;
}
