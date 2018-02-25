package com.galaxy.framework.sagittarius.mapper;

import com.galaxy.framework.sagittarius.entity.LeaseHouseContract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LeaseHouseContractMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LeaseHouseContract record);

    int insertSelective(LeaseHouseContract record);

    LeaseHouseContract selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LeaseHouseContract record);

    int updateByPrimaryKey(LeaseHouseContract record);

    LeaseHouseContract selectByCode(String code);

    LeaseHouseContract getByHouseInUsed(String houseCode);

    List<LeaseHouseContract> findAll(Map<String, Object> params);
}