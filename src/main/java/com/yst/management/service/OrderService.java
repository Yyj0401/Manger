package com.yst.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yst.management.entity.Order;
import com.yst.management.entity.vo.OrderVo;

import java.util.List;

public interface OrderService extends IService<Order> {
    List<OrderVo> getOrderByPage(Integer startPage,Integer pageSize,String orderNumber,Integer isSend,Integer payStatus);

    long getOrderCount(String orderNumber, Integer isSend, Integer payStatus);
    boolean updateOrder(OrderVo orderVo);

    OrderVo getOrderById(Integer id);

    boolean deleteOrderById(Integer id);
    boolean deleteOrderGoodsByOrderId(Integer orderId);
}
