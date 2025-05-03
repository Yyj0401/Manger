package com.yst.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class GoodsPicture {
    @TableId("pics_id")
    private Integer picsId;
    private Integer goodsId;
    private String picsBig;
    private String picsMid;
    private String picsSma;
}
