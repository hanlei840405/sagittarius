package com.galaxy.framework.sagittarius.service;

import com.galaxy.framework.pisces.exception.db.UpdateException;
import com.galaxy.framework.pisces.vo.capricorn.OwnerVo;
import com.galaxy.framework.sagittarius.entity.LeaseHouseContract;
import com.galaxy.framework.sagittarius.mapper.LeaseHouseContractMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LeaseHouseContractService {

    @Autowired
    private LeaseHouseContractMapper leaseHouseContractMapper;
    @Autowired
    private RedisSequenceService redisSequenceService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public LeaseHouseContract selectByCode(String code) {
        return leaseHouseContractMapper.selectByCode(code);
    }

    public List<LeaseHouseContract> findAll(Map<String, Object> search) {
        return leaseHouseContractMapper.findAll(search);
    }

    public LeaseHouseContract getByHouseInUsed(String houseCode) {
        return leaseHouseContractMapper.getByHouseInUsed(houseCode);
    }

    public PageInfo<LeaseHouseContract> page(Map<String, Object> search, int pageNo, int pageSize) {
        PageInfo<LeaseHouseContract> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> findAll(search));
        return pageInfo;
    }

    @Transactional
    public void save(LeaseHouseContract leaseHouseContract) {
        if (!StringUtils.isEmpty(leaseHouseContract.getCode())) { // 执行更新操作
            LeaseHouseContract exist = leaseHouseContractMapper.selectByCode(leaseHouseContract.getCode());
            BeanUtils.copyProperties(leaseHouseContract, exist, "id");
            leaseHouseContractMapper.updateByPrimaryKey(exist);
        } else { // 执行新增
            String code = redisSequenceService.generate(LeaseHouseContract.class.getName());
            leaseHouseContract.setCode(code);
            leaseHouseContract.setStatus("新建");
            leaseHouseContractMapper.insert(leaseHouseContract);
        }
        OwnerVo ownerVo = new OwnerVo();
        ownerVo.setName(leaseHouseContract.getOwnerName());
        ownerVo.setIdNumber(leaseHouseContract.getOwnerId());
        ownerVo.setMobile(leaseHouseContract.getOwnerPhone());
        rabbitTemplate.convertAndSend("basic", "basic.owner.save." + leaseHouseContract.getCode(), ownerVo);
    }

    @Transactional
    public void update(LeaseHouseContract leaseHouseContract) {
        try {
            leaseHouseContractMapper.updateByPrimaryKey(leaseHouseContract);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int deleteByCode(String code) {
        LeaseHouseContract leaseHouseContract = selectByCode(code);
        try {
            leaseHouseContract.setStatus("无效");
            return leaseHouseContractMapper.updateByPrimaryKey(leaseHouseContract);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int reuse(String code) {
        LeaseHouseContract leaseHouseContract = selectByCode(code);
        try {
            leaseHouseContract.setStatus("有效");
            return leaseHouseContractMapper.updateByPrimaryKey(leaseHouseContract);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }
}
