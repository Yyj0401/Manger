package com.yst.management.controller;

import com.yst.management.common.Pager;
import com.yst.management.common.Result;
import com.yst.management.entity.Goods;
import com.yst.management.entity.vo.GoodsVo;
import com.yst.management.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/goods", produces = "application/json;charset=utf-8")
@Tag(name = "商品接口",description = "商品相关接口")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 商品分页查询
     * @param pageNum
     * @param pageSize
     * @param goodsName
     * @return
     */
    @Operation(summary = "商品分页查询")
    @Parameters({
            @Parameter(name = "pageNum",description = "页码"),
            @Parameter(name = "pageSize",description = "每页条数"),
            @Parameter(name = "goodsName",description = "商品名称")
    })
    @GetMapping("/getGoodsByPage")
    public Result<Pager<Goods>> getGoodsByPage(Integer pageNum, Integer pageSize, String goodsName,Integer isPromote,Integer goodsState) {
        List<Goods> goodsByPage = goodsService.getGoodsByPage((pageNum-1)*pageSize,pageSize,goodsName,isPromote,goodsState);
        if(goodsByPage!=null){
            Pager<Goods> pager=new Pager<>();
            pager.setData(goodsByPage);
            pager.setPageNum(pageNum);
            pager.setPageSize(pageSize);
            pager.setTotal(goodsService.getGoodsCount(goodsName,isPromote,goodsState));
            return Result.success(pager);
        }
        return Result.error("未查到数据！");
    }

    /**
     * 通过id查询商品
     * @param id
     * @return
     */
    @Operation(summary = "通过id查询商品")
    @Parameter(name = "id",description = "商品id")
    @GetMapping("/getGoodsById/{id}")
    public Result<GoodsVo> getGoodsById(@PathVariable Integer id) throws UnknownHostException {
        GoodsVo goodsById = goodsService.getGoodsById(id);
        if(goodsById!=null){
            return Result.success(goodsById);
        }else{
            return Result.error("未查到数据！");
        }
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @Operation(summary = "删除商品")
    @Parameter(name = "id",description = "商品id")
    @PutMapping("/deleteGoodsById/{id}")
    public Result<String> deleteGoodsById(@PathVariable Integer id){
        boolean flag = goodsService.deleteGoodsById(id);
        if(flag){
            return Result.success("商品删除成功！");
        }else{
            return Result.error("商品删除失败！");
        }
    }

    /**
     * 添加商品
     * @param goodsVo
     * @return
     */
    @Operation(summary = "添加商品")
    @PostMapping("/addGoods")
    public Result<String> addGoods(@RequestBody GoodsVo goodsVo){
        Goods goodsByName = goodsService.getGoodsByName(goodsVo.getGoodsName());
        if(goodsByName!=null){
            return Result.error("商品名称已存在！");
        }
        boolean flag = goodsService.addGoods(goodsVo);
        if(flag){
            return Result.success("商品添加成功！");
        }else{
            return Result.error("商品添加失败！");
        }
    }
}