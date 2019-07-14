package com.self.service.impl;

import com.self.dao.MengShoppingDAO;
import com.self.entity.GoodsEntity;
import com.self.service.MengShoppingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MengShoppingServiceImpl implements MengShoppingService {
    @Resource
    private MengShoppingDAO mengShoppingDAO;

    public GoodsEntity getOneGoodsFromBase() {
        return mengShoppingDAO.getGoodsInfo();
    }

    public int updateGoodsInfoById(GoodsEntity tempGoodsEntity) {
        return mengShoppingDAO.updateGoodsInfoByID(tempGoodsEntity);
    }
}
