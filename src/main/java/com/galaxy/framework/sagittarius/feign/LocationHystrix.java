package com.galaxy.framework.sagittarius.feign;

import com.galaxy.framework.pisces.vo.aquarius.LocationVo;
import org.springframework.stereotype.Component;

@Component
public class LocationHystrix implements LocationFeign {
    @Override
    public LocationVo get(String code, String status) {
        return null;
    }
}
