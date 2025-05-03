package com.yst.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Attribute {
    @TableId("attr_id")
    private Integer attrId;
    private String attrName;
    private Integer catId;
    private String attrSel;
    private String attrWrite;
    private String attrVals;
    private LocalDateTime deleteTime;
    private Boolean isDelete;
}
