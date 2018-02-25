package com.galaxy.framework.sagittarius.service;

import com.galaxy.framework.pisces.exception.db.UpdateException;
import com.galaxy.framework.sagittarius.entity.ContractFee;
import com.galaxy.framework.sagittarius.mapper.ContractFeeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ContractFeeService {

    @Autowired
    private ContractFeeMapper contractFeeMapper;
    @Autowired
    private RedisSequenceService redisSequenceService;

    public ContractFee selectByCode(String code) {
        return contractFeeMapper.selectByCode(code);
    }



    public List<ContractFee> selectByContractCode(String code) {
        return contractFeeMapper.selectByContractCode(code);
    }

    @Transactional
    public void save(ContractFee contractFee) {
        if (!StringUtils.isEmpty(contractFee.getCode())) { // 执行更新操作
            ContractFee exist = contractFeeMapper.selectByCode(contractFee.getCode());
            BeanUtils.copyProperties(contractFee, exist, "id");
            contractFeeMapper.updateByPrimaryKey(exist);
        } else { // 执行新增
            String code = redisSequenceService.generate(ContractFee.class.getName());
            contractFee.setCode(code);
            contractFeeMapper.insert(contractFee);
        }
    }

    @Transactional
    public void update(ContractFee contractFee) {
        try {
            contractFeeMapper.updateByPrimaryKey(contractFee);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int deleteByCode(String code) {
        ContractFee contractFee = selectByCode(code);
        try {
            contractFee.setStatus("删除");
            return contractFeeMapper.updateByPrimaryKey(contractFee);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int reuse(String code) {
        ContractFee contractFee = selectByCode(code);
        try {
            contractFee.setStatus("启用");
            return contractFeeMapper.updateByPrimaryKey(contractFee);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }
}
