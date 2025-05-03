package com.yst.management.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Manager {
    private static final long serialVersionUID=1L;
    @TableId("id")
    private Long id;
    private String username;
    //@JsonIgnore注解：指示 Jackson 在序列化和反序列化过程中应该忽略被注解的属性（字段），前端不会拿到该属性
    private String password;
    private String email;
    private String phone;
    private String gender;
    private Integer age;
    private String address;
    private String avatarUrl;
    private Integer roleId;   //外键
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean status;
    private Boolean isDeleted;
}
