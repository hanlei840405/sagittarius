package com.galaxy.framework.sagittarius.service;

import com.galaxy.framework.pisces.exception.db.UpdateException;
import com.galaxy.framework.sagittarius.entity.ContractFeeSettle;
import com.galaxy.framework.sagittarius.mapper.ContractFeeSettleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class ContractFeeSettleService {

    @Autowired
    private ContractFeeSettleMapper contractFeeSettleMapper;
    @Autowired
    private RedisSequenceService redisSequenceService;

    public ContractFeeSettle selectByCode(String code) {
        return contractFeeSettleMapper.selectByCode(code);
    }



    public List<ContractFeeSettle> findAll(Map<String, Object> search) {
        return contractFeeSettleMapper.findAll(search);
    }

    public PageInfo<ContractFeeSettle> page(Map<String, Object> search, int pageNo, int pageSize) {
        PageInfo<ContractFeeSettle> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> findAll(search));
        return pageInfo;
    }

    @Transactional
    public void save(ContractFeeSettle contractFeeSettle) {
        if (!StringUtils.isEmpty(contractFeeSettle.getCode())) { // 执行更新操作
            ContractFeeSettle exist = contractFeeSettleMapper.selectByCode(contractFeeSettle.getCode());
            BeanUtils.copyProperties(contractFeeSettle, exist, "id");
            contractFeeSettleMapper.updateByPrimaryKey(exist);
        } else { // 执行新增
            String code = redisSequenceService.generate(ContractFeeSettle.class.getName());
            contractFeeSettle.setCode(code);
            contractFeeSettleMapper.insert(contractFeeSettle);
        }
    }

    @Transactional
    public void update(ContractFeeSettle contractFeeSettle) {
        try {
            contractFeeSettleMapper.updateByPrimaryKey(contractFeeSettle);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int deleteByCode(String code) {
        ContractFeeSettle contractFeeSettle = selectByCode(code);
        try {
            contractFeeSettle.setStatus("删除");
            return contractFeeSettleMapper.updateByPrimaryKey(contractFeeSettle);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int reuse(String code) {
        ContractFeeSettle contractFeeSettle = selectByCode(code);
        try {
            contractFeeSettle.setStatus("启用");
            return contractFeeSettleMapper.updateByPrimaryKey(contractFeeSettle);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }
}
