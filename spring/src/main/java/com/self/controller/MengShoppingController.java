package com.self.controller;

import com.self.entity.GoodsEntity;
import com.self.entity.OrderEntity;
import com.self.service.MengOrderService;
import com.self.service.MengShoppingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * 梦梦的购物平台
 *
 * 设计思路：
 * 1.查询出商品的详细信息，将商品总数保存在 TOTAL 中
 * 2.生成订单信息，并将订单信息插入到订单表中，此时支付状态为未支付；
 * 3.支付功能，用户对已经生成的订单发起支付功能，支付成功后修改订单表中支付状态为已支付。
 *
 * Spring容器事务
 */
@Controller
public class MengShoppingController {

    Logger logger = Logger.getLogger(this.getClass().toString());

    Object object = new Object();

    // 定义商品总数
    private static Integer TOTAL;

    @Resource
    private MengShoppingService mengShoppingService;

    @Resource
    private MengOrderService orderService;

    /**
     * 模拟多用户购买商品
     */
    @RequestMapping("addToCar")
    public void manyCustomersBuy() {
        // 创建i个线程，用于模拟多用户
        for (int i = 0; i < 10; i ++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    synchronized (object) {
                        getOneGoods();
                    }
                }
            };
            thread.start();
        }
    }

    public void getOneGoods() {
        // 查询商品的信息
        GoodsEntity goodsEntity = mengShoppingService.getOneGoodsFromBase();

        if (goodsEntity != null) {
            TOTAL = goodsEntity.getTotal();
        }

        //设置订单详情
        OrderEntity orderEntity = orderDetail(goodsEntity);

        // 获取商品剩余库存数量
        TOTAL -= orderEntity.getBuyCount();

        if (TOTAL < 0) {
            logger.info("商品库存不足！");
            return;
        }

        int result = orderService.addOrderInfo(orderEntity);

        // 订单创建成功，更新商品库存数量
        if (result > 0) {

            // 更新商品库存数量
            GoodsEntity tempGoodsEntity1 = new GoodsEntity();
            tempGoodsEntity1.setId(goodsEntity.getId());
            tempGoodsEntity1.setTotal(TOTAL);
            int amount = mengShoppingService.updateGoodsInfoById(tempGoodsEntity1);
            if (amount > 0) {
                logger.info("商品信息已更新！");
            }
            logger.info("订单状态：" + result + "个订单已生成，请完成支付！");
        } else {
            logger.info("订单生成失败！请重新尝试！！！");
        }

    }

    /**
     * 设置订单详情
     *
     * @return 订单详情对象
     */
    private OrderEntity orderDetail(GoodsEntity goodsEntity) {
        OrderEntity orderEntity = new OrderEntity();

        // 生成唯一id
        String id = UUID.randomUUID().toString().replace("-", "");
        // 客户编号
        String customerNumber = "abcdefghi";
        // 客户姓名
        String customerName = "千千千千千山鸟飞绝";
        // 客户联系方式
        String phoneNumber = "17733139891";
        // 购买数量
        int buyCount = 1;
        // 是否已支付
        int isPaid = 0;
        // 订单创建时间
        Date createTime = new Date();

        orderEntity.setId(id);
        orderEntity.setCustomerName(customerName);
        orderEntity.setCustomerNumber(customerNumber);
        orderEntity.setPhoneNumber(phoneNumber);
        orderEntity.setBuyCount(buyCount);
        orderEntity.setIsPaid(isPaid);
        orderEntity.setCreateTime(createTime);
        orderEntity.setGoodsId(goodsEntity.getId());
        orderEntity.setNumber(goodsEntity.getNumber());

        return orderEntity;
    }

}
