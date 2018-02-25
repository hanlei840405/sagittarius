package com.galaxy.framework.sagittarius.feign;

import com.galaxy.framework.pisces.vo.capricorn.AreaVo;
import org.springframework.stereotype.Component;

@Component
public class AreaHystrix implements AreaFeign{
    @Override
    public AreaVo getByCode(String code) {
        return null;
    }
}
