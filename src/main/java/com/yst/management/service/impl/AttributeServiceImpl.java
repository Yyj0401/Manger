package com.yst.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yst.management.entity.Attribute;
import com.yst.management.mapper.AttributeMapper;
import com.yst.management.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute> implements AttributeService {

    @Autowired
    private AttributeMapper attributeMapper;
    @Override
    public List<Attribute> getAttributeByCatId(Attribute attribute) {
        List<Attribute> attributeByCatId = attributeMapper.getAttributeByCatId(attribute);
        if(attributeByCatId.size()==0){
            return null;
        }
        return attributeByCatId;
    }

    @Override
    public Boolean addAttribute(Attribute attribute) {
        return attributeMapper.addAttribute(attribute);
    }

    @Override
    public Boolean deleteAttribute(Integer attrId) {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); ////注意月和小时的格式为两个大写字母
        Date date=new Date(); //获取当前时间
        String deleteTime=df.format(date); //将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中
        return attributeMapper.deleteAttribute(attrId, deleteTime);
    }

    @Override
    public Attribute getAttributeById(Integer attrId) {
        return attributeMapper.getAttributeById(attrId);
    }

    @Override
    public Boolean updateAttrbute(Attribute attribute) {
        return attributeMapper.updateAttrbute(attribute);
    }
}
