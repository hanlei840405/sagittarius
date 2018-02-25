package com.galaxy.framework.sagittarius.mapper;

import com.galaxy.framework.sagittarius.entity.ContractFeeSettle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContractFeeSettleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContractFeeSettle record);

    int insertSelective(ContractFeeSettle record);

    ContractFeeSettle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractFeeSettle record);

    int updateByPrimaryKey(ContractFeeSettle record);

    ContractFeeSettle selectByCode(String code);

    List<ContractFeeSettle> findAll(Map<String, Object> params);
}