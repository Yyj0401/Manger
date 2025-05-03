package com.yst.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class PermissionApi {
    @TableId("id")
    private Integer id;
    private Integer permissionId; //外键，指向权限详情表
    private String apiService;
    private String apiAction;
    private String apiPath;
    private Integer apiOrder;
    // private Permission permission; //用于关联查询
}
