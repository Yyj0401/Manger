package com.yst.management.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yst.management.entity.dto.RoleCustom;
import lombok.Data;

import java.util.List;

@Data
public class RoleParentChildVo extends RoleCustom {
    // 定义数据库不存在的字段
    @TableField(exist = false)
    private List<PermissionApiParantChildVo> children;
}