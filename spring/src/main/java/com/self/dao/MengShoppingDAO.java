package com.self.dao;

import com.self.entity.GoodsEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MengShoppingDAO {
    GoodsEntity getGoodsInfo();

    int updateGoodsInfoByID(GoodsEntity goodsEntity);
}
