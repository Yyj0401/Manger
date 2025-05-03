package com.yst.management.entity.dto;

import com.yst.management.entity.GoodsPicture;
import lombok.Data;

@Data
public class GoodsPictureCustom extends GoodsPicture {
    private String picsBigUrl;
    private String picsMidUrl;
    private String picsSmaUrl;
}
