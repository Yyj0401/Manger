package com.yst.management.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 自定义角色类
 */
@Data
public class RoleCustom {
    private Integer roleId;
    private String roleName;
    private String permissionIds;  //外键
    private String roleDesc;
}
