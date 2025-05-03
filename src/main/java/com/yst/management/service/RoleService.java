package com.yst.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yst.management.entity.Role;
import com.yst.management.entity.vo.PermissionApiParantChildVo;
import com.yst.management.entity.vo.RoleParentChildVo;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<RoleParentChildVo> getAllRoleTree();

    List<Role> getAllRoleList();

    Role getRoleById(Integer id);

    boolean createRole(Role role);

    boolean updateRole(Role role);

    boolean deleteRole(Integer id);

    boolean setRole(Role role);

    Role getRoleByName(String name);

    Role getRoleByIdAndName(Integer id,String name);

    List<PermissionApiParantChildVo> deleteRolePermissionId(Integer id,Integer permissionId);

    List<PermissionApiParantChildVo> getAllRoleTreeById(Integer id);
}
