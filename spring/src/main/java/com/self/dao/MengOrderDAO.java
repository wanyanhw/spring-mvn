package com.self.dao;

import com.self.entity.OrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MengOrderDAO {
    int addOrderInfo(OrderEntity orderEntity);
}
