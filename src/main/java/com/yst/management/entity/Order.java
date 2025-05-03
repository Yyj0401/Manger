package com.yst.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    // 指明主键
    @TableId("order_id")
    private Integer orderId;
    private Integer userId;
    private String orderNumber;
    private BigDecimal orderPrice;
    private Integer orderPay;
    private Boolean isSend;
    private String tradeNo;
    private String orderInvoiceContent;
    private String consigneeName;
    private String consigneePhone;
    private String consigneeAddressCounty;
    private String consigneeAddressDetail;
    private Boolean payStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
