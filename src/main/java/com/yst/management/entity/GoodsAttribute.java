package com.yst.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class GoodsAttribute {
    @TableId("id")
    private Integer id;
    private Integer goodsId;
    private Integer attrId;
    private String attrValue;
    private BigDecimal addPrice;
}
