package com.yst.management.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yst.management.entity.dto.PermissionCustom;
import lombok.Data;

import java.util.List;

@Data
public class PermissionParentChildVo extends PermissionCustom {
    // 定义数据库不存在的字段
    @TableField(exist = false)
    private List<PermissionParentChildVo> children;
}