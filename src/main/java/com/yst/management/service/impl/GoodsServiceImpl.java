package com.yst.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yst.management.entity.Goods;
import com.yst.management.entity.GoodsAttribute;
import com.yst.management.entity.GoodsPicture;
import com.yst.management.entity.vo.GoodsVo;
import com.yst.management.mapper.GoodsMapper;
import com.yst.management.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ServletWebServerApplicationContext servletWebServerApplicationContext;

    @Override
    public List<Goods> getGoodsByPage(Integer startPage, Integer pageSize, String goodsName,Integer isPromote,Integer goodsState) {
        List<Goods> goodsByPage = goodsMapper.getGoodsByPage(startPage, pageSize, goodsName,isPromote,goodsState);
        if (goodsByPage.size() == 0) {
            return null;
        }
        return goodsByPage;
    }

    @Override
    public long getGoodsCount(String goodsName,Integer isPromote,Integer goodsState) {
        return goodsMapper.getGoodsCount(goodsName,isPromote,goodsState);
    }

    @Override
    public GoodsVo getGoodsById(Integer id) throws UnknownHostException {
        GoodsVo goodsById = goodsMapper.getGoodsById(id);
        if(goodsById==null){
            return null;
        }
        // InetAddress.getLocalHost().getHostAddress()获取当前服务器主机地址
        String localHost=InetAddress.getLocalHost().getHostAddress();
        // ServletWebServerApplicationContext类是web的上下文，可获取当前端口号
        int port = servletWebServerApplicationContext.getWebServer().getPort();
        String localAddress="http://"+localHost+":"+port;
        goodsById.getPics().forEach(item->{
            item.setPicsBigUrl(localAddress.concat(item.getPicsBig()));
            item.setPicsMidUrl(localAddress.concat(item.getPicsMid()));
            item.setPicsSmaUrl(localAddress.concat(item.getPicsSma()));
        });
        goodsById.setGoodsCat(goodsById.getCatOneId() + "," + goodsById.getCatTwoId() + "," + goodsById.getCatThreeId());
        return goodsById;
    }

    @Override
    public boolean deleteGoodsById(Integer id) {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); ////注意月和小时的格式为两个大写字母
        Date date=new Date(); //获取当前时间
        String deleteTime=df.format(date); //将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中
        return goodsMapper.deleteGoodsById(id,deleteTime);
    }

    @Override
    public boolean addGoods(GoodsVo goodsVo) {
        // 构造商品参数
        List<Integer> goodsCats = Arrays.stream(goodsVo.getGoodsCat().split(",")).map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        Goods goods = new Goods();
        goods.setGoodsName(goodsVo.getGoodsName());
        goods.setGoodsPrice(goodsVo.getGoodsPrice());
        goods.setGoodsNumber(goodsVo.getGoodsNumber());
        goods.setGoodsWeight(goodsVo.getGoodsWeight());
        goods.setGoodsIntroduce(goodsVo.getGoodsIntroduce());
        goods.setCatId(goodsCats.get(2));
        goods.setCatOneId(goodsCats.get(0));
        goods.setCatTwoId(goodsCats.get(1));
        goods.setCatThreeId(goodsCats.get(2));
        boolean addGoodFlag = goodsMapper.addGoods(goods);

        // 构造属性参数（数组）
        List<GoodsAttribute> goodsAttributes = new ArrayList<>();
        goodsVo.getAttrs().forEach(item -> {
            GoodsAttribute goodsAttribute=new GoodsAttribute();
            goodsAttribute.setGoodsId(goods.getGoodsId());
            goodsAttribute.setAttrId(item.getAttrId());
            goodsAttribute.setAttrValue(item.getAttrValue());
            goodsAttributes.add(goodsAttribute);
        });
        addBatchAttribute(goodsAttributes);

        // 构造图片参数（数组）
        List<GoodsPicture> goodsPictures = new ArrayList<>();
        goodsVo.getPics().forEach(item -> {
            String picUrl = item.getPicsMid().split("\\\\")[1];
            GoodsPicture goodsPicture = new GoodsPicture();
            goodsPicture.setGoodsId(goods.getGoodsId());
            goodsPicture.setPicsBig("/uploads/goodspics/big_".concat(picUrl));
            goodsPicture.setPicsMid("/uploads/goodspics/mid_".concat(picUrl));
            goodsPicture.setPicsSma("/uploads/goodspics/sma_".concat(picUrl));
            goodsPictures.add(goodsPicture);
        });
        addBatchPicture(goodsPictures);
        return addGoodFlag;
    }

    @Override
    public boolean addBatchAttribute(List<GoodsAttribute> attributes) {
        return goodsMapper.addBatchAttribute(attributes);
    }

    @Override
    public boolean addBatchPicture(List<GoodsPicture> pictures) {
        return goodsMapper.addBatchPicture(pictures);
    }

    @Override
    public Goods getGoodsByName(String goodsName) {
        return goodsMapper.getGoodsByName(goodsName);
    }
}
