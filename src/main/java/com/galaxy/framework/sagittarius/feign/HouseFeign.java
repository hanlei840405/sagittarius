package com.galaxy.framework.sagittarius.feign;

import com.galaxy.framework.pisces.vo.capricorn.HouseVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "basic", fallback = HouseHystrix.class)
public interface HouseFeign {

    @RequestMapping(value = "/house/code", method = RequestMethod.GET)
    HouseVo getByCode(@RequestParam(value = "code") String code);

    @RequestMapping(value = "/house/floor", method = RequestMethod.GET)
    List<HouseVo> findByFloor(@RequestParam(value = "floorCode") String floorCode);
}
