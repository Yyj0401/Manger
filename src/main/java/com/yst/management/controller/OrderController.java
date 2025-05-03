package com.yst.management.controller;

import com.yst.management.common.Pager;
import com.yst.management.common.Result;
import com.yst.management.entity.vo.OrderVo;
import com.yst.management.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders",produces = "application/json;charset=utf-8")
@Tag(name = "订单接口",description = "订单相关接口")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 订单分页查询
     * @param pageNum
     * @param pageSize
     * @param orderNumber
     * @param isSend
     * @param payStatus
     * @return
     */
    @Operation(summary = "订单分页查询")
    @Parameters({
            @Parameter(name = "pageNum",description = "页码"),
            @Parameter(name = "pageSize",description = "每页条数"),
            @Parameter(name = "orderNumber",description = "订单编号"),
            @Parameter(name = "isSend",description = "是否发货"),
            @Parameter(name = "payStatus",description = "支付状态")
    })
    @GetMapping("/getOrderByPage")
    public Result<Pager<OrderVo>> getOrderByPage(Integer pageNum, Integer pageSize, String orderNumber, Integer isSend, Integer payStatus){
        List<OrderVo> managerByPage = orderService.getOrderByPage((pageNum-1)*pageSize, pageSize, orderNumber, isSend, payStatus);
        if(managerByPage!=null){
            Pager<OrderVo> orderPager=new Pager<>();
            orderPager.setData(managerByPage);
            orderPager.setPageNum(pageNum);
            orderPager.setPageSize(pageSize);
            orderPager.setTotal(orderService.getOrderCount(orderNumber,isSend,payStatus));
            return Result.success(orderPager);
        }else{
            return Result.error("未查到数据！");
        }
    }

    /**
     * 通过id查询订单：即订单详情，还得查出所有订单对应商品信息！！！
     * @param id
     * @return
     */
    @Operation(summary = "通过id查询订单")
    @Parameter(name = "id",description = "订单id")
    @GetMapping("/getOrderById/{id}")
    public Result<OrderVo> getOrderById(@PathVariable Integer id){
        OrderVo orderById = orderService.getOrderById(id);
        if(orderById!=null){
            return Result.success(orderById);
        }else{
            return Result.error("未查到数据！");
        }
    }

    /**
     * 修改订单信息
     * @param orderVo
     * @return
     */
    @Operation(summary = "修改订单")
    @PutMapping("/updateOrder")
    public Result<String> updateOrder(@RequestBody OrderVo orderVo){
        boolean flag = orderService.updateOrder(orderVo);
        if(flag){
            return Result.success("订单修改成功！");
        }else{
            return Result.error("订单修改失败！");
        }
    }

    /**
     * 删除订单信息
     * @param id
     * @return
     */
    @Operation(summary = "删除订单")
    @Parameter(name = "id",description = "订单id")
    @DeleteMapping("/deleteOrderById/{id}")
    public Result<String> deleteOrderById(@PathVariable Integer id){
        boolean flag = orderService.deleteOrderById(id);
        if(flag){
            return Result.success("订单删除成功！");
        }else{
            return Result.error("订单删除失败！");
        }
    }
}
