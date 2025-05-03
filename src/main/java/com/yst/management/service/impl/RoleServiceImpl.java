package com.yst.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yst.management.entity.Role;
import com.yst.management.entity.vo.PermissionApiParantChildVo;
import com.yst.management.entity.vo.RoleParentChildVo;
import com.yst.management.mapper.RoleMapper;
import com.yst.management.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleParentChildVo> getAllRoleTree() {
        List<Role> roleList=getAllRoleList();
        if(roleList==null){
            return null;
        }
        List<RoleParentChildVo> roleParentChildVoList=new ArrayList<>();
        roleList.forEach(item->{
            RoleParentChildVo roleParentChildVo=new RoleParentChildVo();
            roleParentChildVo.setRoleId(item.getId());
            roleParentChildVo.setRoleName(item.getName());
            roleParentChildVo.setPermissionIds(item.getPermissionIds());
            roleParentChildVo.setRoleDesc(item.getDescription());
            roleParentChildVo.setChildren(getAllRoleTreeById(item.getId()));
            roleParentChildVoList.add(roleParentChildVo);
        });
        return roleParentChildVoList;
    }

    @Override
    public List<Role> getAllRoleList() {
        List<Role> allRoleList = roleMapper.getAllRoleList();
        if(allRoleList.size()==0){
            return null;
        }
        return allRoleList;
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public boolean createRole(Role role) {
        return roleMapper.createRole(role);
    }

    @Override
    public boolean updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public boolean deleteRole(Integer id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    public boolean setRole(Role role) {
        return roleMapper.setRole(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleMapper.getRoleByName(name);
    }

    @Override
    public Role getRoleByIdAndName(Integer id, String name) {
        return roleMapper.getRoleByIdAndName(id,name);
    }

    @Override
    public List<PermissionApiParantChildVo> deleteRolePermissionId(Integer id,Integer permissionId) {
        Role roleById = getRoleById(id);
        String newPermissionIds = removeOne(roleById.getPermissionIds(), permissionId);
        Role role=new Role();
        role.setId(id);
        role.setPermissionIds(newPermissionIds);
        setRole(role);
        return getAllRoleTreeById(id);
    }

    @Override
    public List<PermissionApiParantChildVo> getAllRoleTreeById(Integer id) {
        List<PermissionApiParantChildVo> allRoleTree = roleMapper.getAllRoleTreeById(id);
        if(allRoleTree.size()==0){
            return null;
        }
        return allRoleTree;
    }

    /**
     * 移除以逗号分隔的字符串中指定元素
     * @param ids
     * @param id
     * @return
     */
    public static String removeOne(String ids, Integer id) {
        // 返回结果
        String result = "";
        // 判断是否存在。如果存在，移除指定用户 ID；如果不存在，则直接返回空
        if(ids.indexOf(",") != -1) {
            // 拆分成数组
            String[] idArray = ids.split(",");
            // 将数组转化成List集合的方法
            List<String> idList = new ArrayList<String>(Arrays.asList(idArray));
            // 移除指定用户 ID
            idList.remove(id.toString());
            // 把剩下的用户 ID 再拼接起来
            result = StringUtils.join(idList, ",");
        }
        // 返回
        return result;
    }
}
