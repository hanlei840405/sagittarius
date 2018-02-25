package com.galaxy.framework.sagittarius.mapper;

import com.galaxy.framework.sagittarius.entity.ContractFee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractFeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContractFee record);

    int insertSelective(ContractFee record);

    ContractFee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractFee record);

    int updateByPrimaryKey(ContractFee record);

    ContractFee selectByCode(String code);

    List<ContractFee> selectByContractCode(String contractCode);
}