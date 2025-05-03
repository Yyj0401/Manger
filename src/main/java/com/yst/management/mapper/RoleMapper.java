package com.yst.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yst.management.entity.Role;
import com.yst.management.entity.dto.PermissionCustom;
import com.yst.management.entity.vo.PermissionApiParantChildVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    // 获取角色列表
    List<Role> getAllRoleList();

    // 获取角色权限树状列表
    List<PermissionApiParantChildVo> getAllRoleTreeById(Integer roleId);

    // 根据角色id获取角色信息
    Role getRoleById(Integer id);

    // 创建角色
    boolean createRole(Role role);

    // 更新角色
    boolean updateRole(Role role);

    // 删除角色
    boolean deleteRole(Integer id);

    // 角色权限分配
    boolean setRole(Role role);

    // 根据角色名查询角色：创建角色防止重复角色名
    Role getRoleByName(String name);

    // 根据角色id和角色名查询角色：角色防止重复角色名
    Role getRoleByIdAndName(@Param("id") Integer id,@Param("name") String name);

    PermissionCustom getPermissionByRoleId(Integer roleId);
}
