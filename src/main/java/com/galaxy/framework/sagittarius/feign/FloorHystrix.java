package com.galaxy.framework.sagittarius.feign;

import com.galaxy.framework.pisces.vo.capricorn.FloorVo;
import org.springframework.stereotype.Component;

@Component
public class FloorHystrix implements FloorFeign{
    @Override
    public FloorVo getByCode(String code) {
        return null;
    }
}
