package com.yst.management.controller;

import com.yst.management.common.Result;
import com.yst.management.entity.Attribute;
import com.yst.management.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/attributes",produces = "application/json;charset=utf-8")
@Tag(name = "属性接口",description = "属性相关接口")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    /**
     * 通过分类获取属性列表：静态属性或动态属性
     * @param attribute
     * @return
     */
    @Operation(summary = "通过分类获取属性列表")
    @PostMapping("/getAttributeByCatId")
    public Result<List<Attribute>> getAttributeByCatId(@RequestBody Attribute attribute){
        List<Attribute> attributeByCatId = attributeService.getAttributeByCatId(attribute);
        if(attributeByCatId!=null){
            return Result.success(attributeByCatId);
        }else{
            return Result.error("未查到数据！");
        }
    }

    /**
     * 添加属性：动态属性或者静态属性
     * @param attribute
     * @return
     */
    @Operation(summary = "添加属性")
    @PostMapping("/addAttribute")
    public Result<String> addAttribute(@RequestBody Attribute attribute){
        Boolean flag = attributeService.addAttribute(attribute);
        if(flag){
            return Result.success("属性添加成功！");
        }else{
            return Result.error("属性添加失败！");
        }
    }

    /**
     * 删除属性
     * @param attrId
     * @return
     */
    @Operation(summary = "删除属性")
    @Parameter(name = "attrId",description = "属性id")
    @DeleteMapping("/deleteAttribute/{attrId}")
    public Result<String> deleteAttribute(@PathVariable Integer attrId){
        Boolean flag = attributeService.deleteAttribute(attrId);
        if(flag){
            return Result.success("属性删除成功！");
        }else{
            return Result.success("属性删除失败！");
        }
    }

    /**
     * 根据ID查询属性
     * @param attrId
     * @return
     */
    @Operation(summary = "根据ID查询属性")
    @Parameter(name = "attrId",description = "属性id")
    @GetMapping("/getAttributeById/{attrId}")
    public Result<Attribute> getAttributeById(@PathVariable Integer attrId){
        Attribute attributeById = attributeService.getAttributeById(attrId);
        if(attributeById!=null){
            return Result.success(attributeById);
        }else{
            return Result.error("未查到数据！");
        }
    }

    /**
     * 修改属性
     * @param attribute
     * @return
     */
    @Operation(summary = "修改属性")
    @PutMapping("/updateAttrbute")
    public Result<String> updateAttrbute(@RequestBody Attribute attribute){
        Boolean flag = attributeService.updateAttrbute(attribute);
        if(flag){
            return Result.success("属性修改成功！");
        }else{
            return Result.success("属性修改失败！");
        }
    }
}
