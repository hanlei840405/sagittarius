package com.galaxy.framework.sagittarius.feign;

import com.galaxy.framework.pisces.vo.capricorn.AreaVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "basic", fallback = AreaHystrix.class)
public interface AreaFeign {

    @RequestMapping(value = "/area/code", method = RequestMethod.GET)
    AreaVo getByCode(@RequestParam(value = "code") String code);
}
