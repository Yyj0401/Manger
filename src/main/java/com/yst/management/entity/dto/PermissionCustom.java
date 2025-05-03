package com.yst.management.entity.dto;

import com.yst.management.entity.Permission;
import lombok.Data;

/**
 * 自定义权限类：permission与permission_api连表查询
 */
@Data
public class PermissionCustom extends Permission{
    private Integer id;
    private Integer permissionId; //外键，指向权限详情表
    private String apiService;
    private String apiAction;
    private String apiPath;
    private Integer apiOrder;
}
