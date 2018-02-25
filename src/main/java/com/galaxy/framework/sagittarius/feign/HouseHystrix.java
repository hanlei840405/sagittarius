package com.galaxy.framework.sagittarius.feign;

import com.galaxy.framework.pisces.vo.capricorn.HouseVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HouseHystrix implements HouseFeign{
    @Override
    public HouseVo getByCode(String code) {
        return null;
    }

    @Override
    public List<HouseVo> findByFloor(String floorCode) {
        return null;
    }
}
