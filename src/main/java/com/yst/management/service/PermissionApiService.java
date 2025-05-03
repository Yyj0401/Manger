package com.yst.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yst.management.entity.PermissionApi;
import com.yst.management.entity.dto.PermissionCustom;
import com.yst.management.entity.vo.PermissionParentChildVo;

import java.util.List;

public interface PermissionApiService extends IService<PermissionApi> {
    List<PermissionCustom> getAllRightList();

    List<PermissionParentChildVo> getAllRightTree();

    List<PermissionParentChildVo> getMenus();
}
