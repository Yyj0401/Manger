package com.yst.management.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yst.management.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo extends Category {
    // 新增type字段，用于父级向子集传递能级筛选条件
    @TableField(exist = false)
    private Integer type;
    @TableField(exist = false)
    private List<CategoryVo> children;
}
