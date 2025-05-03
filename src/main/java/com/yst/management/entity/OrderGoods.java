package com.yst.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderGoods{
    @TableId("id")
    private Integer id;
    private Integer orderId;
    private Integer goodsId;
    private BigDecimal goodsPrice;
    private Integer goodsNumber;
    private BigDecimal goodsTotalPrice;
    private String goodsName;
    private String goodsWeight;
}
