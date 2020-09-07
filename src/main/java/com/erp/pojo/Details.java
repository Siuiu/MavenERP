package com.erp.pojo;
/*
采购单明细类
 */
public class Details {
    //明细单id
    private Integer id;
    //绑定采购单id主键
    private String pid;
    //类型
    private ProductType type;
    //商品
    private Product product;
    //商品id
    private Integer productId;
    //商品数量
    private Integer count;
    //商品单位
    private String productUnit;
    //商品进价
    private Double price;
    //商品总价
    private Double money;

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", pid='" + pid + '\'' +
                ", type=" + type +
                ", product=" + product +
                ", productId=" + productId +
                ", count=" + count +
                ", productUnit='" + productUnit + '\'' +
                ", price=" + price +
                ", money=" + money +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
