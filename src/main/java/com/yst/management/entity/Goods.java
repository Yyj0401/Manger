package com.yst.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Goods {
    @TableId("goods_id")
    private Integer goodsId;
    private String goodsName;
    private BigDecimal goodsPrice;
    private Integer goodsNumber;
    private Integer goodsWeight;
    private Integer catId;
    private String goodsIntroduce;
    private String goodsBigLogo;
    private String goodsSmallLogo;
    private Integer catOneId;
    private Integer catTwoId;
    private Integer catThreeId;
    private Integer hotNumber;
    private Boolean isPromote;
    private Integer goodsState;
    private Boolean isDelete;
    private LocalDateTime deleteTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
