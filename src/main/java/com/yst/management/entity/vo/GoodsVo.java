package com.yst.management.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yst.management.entity.Goods;
import com.yst.management.entity.dto.AttributeCustom;
import com.yst.management.entity.dto.GoodsPictureCustom;
import lombok.Data;

import java.util.List;

@Data
public class GoodsVo extends Goods {
    // 存分类id：逗号隔开
    @TableField(exist = false)
    private String goodsCat;
    @TableField(exist = false)
    private List<GoodsPictureCustom> pics;
    @TableField(exist = false)
    private List<AttributeCustom> attrs;
}
