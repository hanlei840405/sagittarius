package com.galaxy.framework.sagittarius.entity;

import com.galaxy.framework.pisces.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author
 */
@Setter
@Getter
public class ContractFeeSettle extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7197719448439010114L;

    private String code;

    private String name;

    private BigDecimal amount;

    private BigDecimal payment;

    private String period;
}