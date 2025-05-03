package com.yst.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yst.management.entity.Attribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AttributeMapper extends BaseMapper<Attribute> {

    // 通过分类获取参数列表：静态参数或动态参数
    List<Attribute> getAttributeByCatId(Attribute attribute);

    // 添加属性：动态参数或者静态属性
    Boolean addAttribute(Attribute attribute);

    // 删除参数：将is_delete字段更新为1，并设置删除时间
    Boolean deleteAttribute(@Param("attrId") Integer attrId,@Param("deleteTime") String deleteTime);

    // 根据ID查询参数
    Attribute getAttributeById(Integer attrId);

    // 编辑提交参数
    Boolean updateAttrbute(Attribute attribute);
}

