package com.yst.management.entity.vo;

import com.yst.management.entity.Manager;
import lombok.Data;

@Data
public class ManagerVo extends Manager {
    private String roleName;
    private String roleDescription;
}
