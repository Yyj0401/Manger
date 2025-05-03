package com.yst.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yst.management.entity.PermissionApi;
import com.yst.management.entity.dto.PermissionCustom;
import com.yst.management.entity.vo.PermissionParentChildVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionApiMapper extends BaseMapper<PermissionApi> {
    // 获取权限列表
    List<PermissionCustom> getAllRightList();
    // 获取权限树状列表
    List<PermissionParentChildVo> getAllRightTree();
    // 获取权限菜单
    List<PermissionParentChildVo> getMenus();
}
