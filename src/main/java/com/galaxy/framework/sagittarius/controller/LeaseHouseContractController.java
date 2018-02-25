package com.galaxy.framework.sagittarius.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galaxy.framework.pisces.exception.rule.EmptyException;
import com.galaxy.framework.pisces.vo.capricorn.HouseVo;
import com.galaxy.framework.sagittarius.entity.LeaseHouseContract;
import com.galaxy.framework.sagittarius.feign.HouseFeign;
import com.galaxy.framework.sagittarius.service.LeaseHouseContractService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leaseHouseContract")
public class LeaseHouseContractController {


    @Autowired
    private LeaseHouseContractService leaseHouseContractService;

    @Autowired
    private HouseFeign houseFeign;

    private ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/page")
    public PageInfo<LeaseHouseContract> page(String search, Integer pageNo, Integer pageSize) throws IOException {

        PageInfo<LeaseHouseContract> pageInfo = leaseHouseContractService.page(objectMapper.readValue(search, new TypeReference<Map<String, Object>>() {
        }), pageNo, pageSize);
        return pageInfo;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/findAll")
    public List<LeaseHouseContract> findAll(String locationCode, String buildingCode, String floorCode, String areaCode, String status) {
        Map<String, Object> search = new HashMap<>();
        search.put("locationCode", locationCode);
        search.put("buildingCode", buildingCode);
        search.put("floorCode", floorCode);
        search.put("areaCode", areaCode);
        search.put("status", status);
        List<LeaseHouseContract> contracts = leaseHouseContractService.findAll(search);
        return contracts;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save")
    public LeaseHouseContract save(@RequestBody LeaseHouseContract leaseHouseContract) {
        if (leaseHouseContract != null) {
            leaseHouseContractService.save(leaseHouseContract);
            return leaseHouseContract;
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public int delete(@RequestBody LeaseHouseContract leaseHouseContract) {
        if (leaseHouseContract != null && !StringUtils.isEmpty(leaseHouseContract.getCode())) {
            return leaseHouseContractService.deleteByCode(leaseHouseContract.getCode());
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/reuse")
    public int reuse(@RequestBody LeaseHouseContract leaseHouseContract) {
        if (leaseHouseContract != null && !StringUtils.isEmpty(leaseHouseContract.getCode())) {
            return leaseHouseContractService.reuse(leaseHouseContract.getCode());
        }
        throw new EmptyException();
    }

    /**
     * 查询可用房源
     * @param floorCode
     * @return
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/availableHouses")
    public List<HouseVo> findAvailableHouses(String floorCode) {
        List<HouseVo> houseVos = houseFeign.findByFloor(floorCode);
        List<HouseVo> result = Lists.newArrayList();
        houseVos.forEach(houseVo -> {
            if (!"失效".equals(houseVo.getStatus()) && !"删除".equals(houseVo.getStatus())) {
                LeaseHouseContract leaseHouseContract = leaseHouseContractService.getByHouseInUsed(houseVo.getCode());
                if (leaseHouseContract == null) {
                    result.add(houseVo);
                }
            }
        });
        return result;
    }
}
