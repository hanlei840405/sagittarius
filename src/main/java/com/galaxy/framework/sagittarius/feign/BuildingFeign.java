package com.galaxy.framework.sagittarius.feign;

import com.galaxy.framework.pisces.vo.capricorn.BuildingVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "basic", fallback = BuildingHystrix.class)
public interface BuildingFeign {

    @RequestMapping(value = "/building/code", method = RequestMethod.GET)
    BuildingVo getByCode(@RequestParam(value = "code") String code);
}
