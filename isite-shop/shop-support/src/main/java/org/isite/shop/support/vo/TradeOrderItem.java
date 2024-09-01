package org.isite.shop.support.vo;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.lang.data.Vo;
import org.isite.shop.support.enums.SpuType;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class TradeOrderItem extends Vo<Long> {
    /**
     * 商品id
     */
    private Integer skuId;
    /**
     * 商品类型
     */
    private SpuType spuType;
    /**
     * 商品名称
     */
    private String skuName;
    /**
     * 商品数量
     */
    private Integer skuCount;
    /**
     * 前台划线价
     */
    private Integer marketPrice;
    /**
     * 成本单价
     */
    private Integer costPrice;
    /**
     * 销售单价
     */
    private Integer salePrice;
    /**
     * 优惠金额
     */
    private Integer discountPrice;
    /**
     * 实际支付金额
     */
    private Integer payPrice;
    /**
     * 服务费
     */
    private Integer serviceCharge;
    /**
     * 支付积分
     */
    private Integer payScore;
    /**
     * 供应商自定义参数(Sku的supplierParam)
     */
    private String supplierParam;
}
