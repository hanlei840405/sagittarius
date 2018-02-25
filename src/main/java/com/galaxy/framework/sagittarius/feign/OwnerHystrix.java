package com.galaxy.framework.sagittarius.feign;

import com.galaxy.framework.pisces.vo.capricorn.OwnerVo;
import org.springframework.stereotype.Component;

@Component
public class OwnerHystrix implements OwnerFeign{
    @Override
    public OwnerVo getByCode(String code) {
        return null;
    }
}
