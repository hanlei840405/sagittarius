package com.galaxy.framework.sagittarius.feign;

import com.galaxy.framework.pisces.vo.capricorn.OwnerVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "basic", fallback = OwnerHystrix.class)
public interface OwnerFeign {

    @RequestMapping(value = "/owner/code", method = RequestMethod.GET)
    OwnerVo getByCode(@RequestParam(value = "code") String code);
}
