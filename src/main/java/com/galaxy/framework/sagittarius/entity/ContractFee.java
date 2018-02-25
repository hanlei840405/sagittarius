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
public class ContractFee extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3063921285679435738L;

    private String code;

    private String contractCode;

    private String name;

    private BigDecimal amount;

    private BigDecimal payment;

    private BigDecimal discount;
}