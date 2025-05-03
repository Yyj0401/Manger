package com.yst.management.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class PermissionApiParantChildVo {
    private Integer permissionId;
    private Integer permissionPid;
    private String permissionName;
    private String apiPath;
    @TableField(exist = false)
    private List<PermissionApiParantChildVo> children;
}
