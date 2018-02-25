package com.galaxy.framework.sagittarius.entity;

import com.galaxy.framework.pisces.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
@Setter
@Getter
public class LeaseHouseContract extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 35027249742820922L;

    private String code;

    private String name;

    private String ownerId;

    private String ownerName;

    private String ownerPhone;

    private String houseCode;

    private String houseName;

    /**
     * 房源位置描述：属地、楼号、楼层等
     */
    private String houseDescr;

    /**
     * 面积
     */
    private BigDecimal houseAcreage;

    /**
     * 公摊面积
     */
    private BigDecimal housePublicArea;

    /**
     * 租金
     */
    private BigDecimal houseRent;

    private BigDecimal amount;

    private BigDecimal periodAmount;

    private BigDecimal deposit;

    private BigDecimal payment;

    private BigDecimal discount;

    private String payMode;

    private String settlementPeriod;

    private Date serviceFrom;

    private Date serviceTo;
}