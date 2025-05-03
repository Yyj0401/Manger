package com.yst.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yst.management.entity.Order;
import com.yst.management.entity.vo.OrderVo;
import com.yst.management.mapper.OrderMapper;
import com.yst.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderVo> getOrderByPage(Integer startPage, Integer pageSize, String orderNumber, Integer isSend, Integer payStatus) {
        List<OrderVo> orderByPage = orderMapper.getOrderByPage(startPage, pageSize, orderNumber, isSend, payStatus);
        if(orderByPage.size()==0){
            return null;
        }
        return orderByPage;
    }

    @Override
    public long getOrderCount(String orderNumber, Integer isSend, Integer payStatus) {
        return orderMapper.getOrderCount(orderNumber,isSend,payStatus);
    }

    @Override
    public OrderVo getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public boolean updateOrder(OrderVo orderVo) {
        return orderMapper.updateOrder(orderVo);
    }

    /**
     * 删除订单：同时删除订单数据和对应的订单商品数据，才算删除成功！！！
     * @param id
     * @return
     */
    @Override
    public boolean deleteOrderById(Integer id) {
        boolean flagOrderGoods = deleteOrderGoodsByOrderId(id);
        boolean flagOrder = orderMapper.deleteOrderById(id);
        return flagOrderGoods&&flagOrder;
    }

    @Override
    public boolean deleteOrderGoodsByOrderId(Integer orderId) {
        return orderMapper.deleteOrderGoodsByOrderId(orderId);
    }

}
