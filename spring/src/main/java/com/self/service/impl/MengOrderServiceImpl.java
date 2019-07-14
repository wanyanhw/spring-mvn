package com.self.service.impl;

import com.self.dao.MengOrderDAO;
import com.self.entity.OrderEntity;
import com.self.service.MengOrderService;
import com.self.utils.DateTransfUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MengOrderServiceImpl implements MengOrderService {

    private DateTransfUtils dateTransfUtils = new DateTransfUtils();

    @Resource
    private MengOrderDAO mengOrderDAO;

    public int addOrderInfo(OrderEntity orderEntity) {

        String createTimeString = dateTransfUtils.dateToString(orderEntity.getCreateTime());

        // 订单编号（32）=商品编号（9）+创建时间（14）+用户编号（9）
        String number = orderEntity.getNumber() + createTimeString + orderEntity.getCustomerNumber();

        orderEntity.setNumber(number);
        return mengOrderDAO.addOrderInfo(orderEntity);
    }
}
