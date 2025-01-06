package org.isite.shop.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;
import org.isite.mybatis.type.EnumTypeHandler;
import org.isite.shop.support.enums.SpuSupplier;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;

/**
 * @Description 在电商领域，SKU（Stock Keeping Unit）和SPU（Standard Product Unit）用于管理和描述商品信息，以便于库存管理和用户搜索。
 * SKU（Stock Keeping Unit）是库存单位，是商品库存管理的最小单位。每个SKU都有一个唯一的标识符，用于区分不同的商品。SKU通常包含以下信息：
 * 商品的唯一标识符（如条形码、二维码等）、商品的属性（如颜色、尺寸、款式等）、商品的价格、商品的库存数量。
 * SPU是标准产品单元，是商品信息聚合的最小单位。一个SPU代表一个商品的基本信息，不包含具体的属性差异。SPU通常包含以下信息：
 * 商品的名称、商品的描述、商品的主图、商品的品牌、商品的分类。
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "spu")
public class SpuPo extends Po<Integer> {
    /**
     * 产品名称
     */
    private String spuName;
    /**
     * 供应商
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private SpuSupplier supplier;
    /**
     * 供应商自定义参数
     */
    private String supplierParam;
}