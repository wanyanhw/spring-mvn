package com.self.entity;

import java.io.Serializable;

/**
 * 商品实体类
 */
public class GoodsEntity implements Serializable {

    private String id; // id
    private String number; // 商品编号
    private String name; // 商品名称
    private Integer total; // 商品总数
    private Float price; // 商品单价

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
