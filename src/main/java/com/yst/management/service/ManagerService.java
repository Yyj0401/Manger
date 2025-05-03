package com.yst.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yst.management.entity.Manager;
import com.yst.management.entity.vo.ManagerVo;
import com.yst.management.entity.vo.ManagerLoginVo;

import java.util.List;

public interface ManagerService extends IService<Manager> {
     List<ManagerVo> findManagerByPage(Integer startPage, Integer pageSize, String username);
     long findManagerCount(String username);
     ManagerLoginVo login(ManagerLoginVo managerLoginVo);
     boolean savaManager(Manager manager);
     boolean deleteManagerById(long id);
     int batchDeleteManager(List<Long> ids);
     boolean updateManagerById(Manager manager);
     boolean updateManagerRoleId(Manager manager);
     ManagerVo findManagerById(long id);
     Manager findManagerByName(String username);
     Manager findManagerByIdAndName(long id,String username);
     Boolean updateManagerStatus(Manager manager);
}
