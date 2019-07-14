package com.self.service;

import com.self.entity.GoodsEntity;

public interface MengShoppingService {
    GoodsEntity getOneGoodsFromBase();

    int updateGoodsInfoById(GoodsEntity tempGoodsEntity1);
}
