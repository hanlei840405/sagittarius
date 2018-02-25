package com.galaxy.framework.sagittarius.feign;

import com.galaxy.framework.pisces.vo.capricorn.BuildingVo;
import org.springframework.stereotype.Component;

@Component
public class BuildingHystrix implements BuildingFeign{
    @Override
    public BuildingVo getByCode(String code) {
        return null;
    }
}
