package com.yst.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yst.management.entity.Manager;
import com.yst.management.entity.vo.ManagerVo;
import com.yst.management.entity.vo.ManagerLoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {
    // 分页查询
    List<ManagerVo> findManagerByPage(@Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize, @Param("username") String username);

    // 总数查询
    long findManagerCount(String username);

    // 登录
    ManagerLoginVo login(ManagerLoginVo managerLoginVo);

    // 新增用户
    boolean savaManager(Manager manager);

    // 删除用户
    boolean deleteManagerById(long id);

    // 批量删除用户
    int batchDeleteManager(@Param("ids") List<Long> ids);

    // 更新用户：更新多个变量
    boolean updateManagerById(Manager manager);

    // 用户角色分配：更新一个变量
    boolean updateManagerRoleId(Manager manager);

    // 根据Id查询用户
    ManagerVo findManagerById(long id);

    // 根据用户名查询用户
    Manager findManagerByName(String username);

    // 根据用户id和用户名查询用户
    Manager findManagerByIdAndName(@Param("id") long id,@Param("username") String username);

    // 修改管理员状态
    Boolean updateManagerStatus(Manager manager);
}

