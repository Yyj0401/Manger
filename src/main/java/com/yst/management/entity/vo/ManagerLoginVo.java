package com.yst.management.entity.vo;

import com.yst.management.entity.Manager;
import lombok.Data;

@Data
public class ManagerLoginVo extends Manager {
    private String token;
}
