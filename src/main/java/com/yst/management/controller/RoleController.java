package com.yst.management.controller;

import com.yst.management.common.Result;
import com.yst.management.entity.Role;
import com.yst.management.entity.vo.PermissionApiParantChildVo;
import com.yst.management.entity.vo.RoleParentChildVo;
import com.yst.management.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/roles", produces = "application/json;charset=utf-8")
@Tag(name = "角色接口", description = "角色相关接口")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取角色权限树状列表
     *
     * @return
     */
    @Operation(summary = "获取角色权限tree")
    @GetMapping("/getAllRoleTree")
    public Result<List<RoleParentChildVo>> getAllRoleTree() {
        List<RoleParentChildVo> roleParentChildVoList = roleService.getAllRoleTree();
        if (roleParentChildVoList != null) {
            return Result.success(roleParentChildVoList);
        } else {
            return Result.error("未查到数据！");
        }
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @Operation(summary = "获取角色列表list")
    @GetMapping("/getAllRoleList")
    public Result<List<Role>> getAllRoleList() {
        List<Role> allRoleList = roleService.getAllRoleList();
        if (allRoleList != null) {
            return Result.success(allRoleList);
        } else {
            return Result.error("未查到数据");
        }
    }

    /**
     * 根据角色id获取角色信息
     *
     * @param id
     * @return
     */
    @Operation(summary = "通过id获取角色")
    @Parameter(name = "id", description = "角色id")
    @GetMapping("/getRoleById/{id}")
    public Result<Role> getRoleById(@PathVariable Integer id) {
        Role roleById = roleService.getRoleById(id);
        if (roleById != null) {
            return Result.success(roleById);
        } else {
            return Result.error("未查到数据!");
        }
    }

    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    @Operation(summary = "创建角色")
    @PostMapping("/createRole")
    public Result<String> createRole(@RequestBody Role role) {
        Role roleByName = roleService.getRoleByName(role.getName());
        if (roleByName != null) {
            return Result.error("角色名已存在！");
        }
        boolean flag = roleService.createRole(role);
        if (flag) {
            return Result.success("角色添加成功！");
        } else {
            return Result.error("角色添加失败！");
        }
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @Operation(summary = "更新角色")
    @PutMapping("/updateRole")
    public Result<String> updateRole(@RequestBody Role role) {
        Role roleByIdAndName = roleService.getRoleByIdAndName(role.getId(), role.getName());
        if (roleByIdAndName != null) {
            return Result.error("角色名已存在！");
        }
        boolean flag = roleService.updateRole(role);
        if (flag) {
            return Result.success("用户修改成功！");
        } else {
            return Result.error("用户修改失败！");
        }
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除角色")
    @Parameter(name = "id", description = "角色id")
    @DeleteMapping("/deleteRole/{id}")
    public Result<String> deleteRole(@PathVariable Integer id) {
        boolean flag = roleService.deleteRole(id);
        if (flag) {
            return Result.success("角色删除成功！");
        } else {
            return Result.error("角色删除失败！");
        }
    }

    /**
     * 角色权限分配
     *
     * @param role
     * @return
     */
    @Operation(summary = "角色权限分配")
    @PutMapping("/setRole")
    public Result<String> setRole(@RequestBody Role role) {
        boolean flag = roleService.setRole(role);
        if (flag) {
            return Result.success("角色权限分配成功！");
        } else {
            return Result.error("角色权限分配失败！");
        }
    }

    /**
     * 删除角色权限
     * @param id
     * @param permissionId
     * @return
     */
    @Operation(summary = "删除角色权限")
    @Parameters({
            @Parameter(name = "id", description = "角色id"),
            @Parameter(name = "permissionId", description = "权限id")
    })
    @DeleteMapping("/deleteRolePermissionId")
    public Result<List<PermissionApiParantChildVo>> deleteRolePermissionId(Integer id, Integer permissionId) {
        List<PermissionApiParantChildVo> permissionApiParantChildVoList = roleService.deleteRolePermissionId(id, permissionId);
        if(permissionApiParantChildVoList!=null){
            return Result.success(permissionApiParantChildVoList);
        }else{
            return Result.error("角色权限删除失败！");
        }
    }

}
