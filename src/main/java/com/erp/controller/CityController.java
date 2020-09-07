package com.erp.controller;




import com.erp.service.CityService;
import com.erp.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class CityController {

    @Resource
    private CityService cityService;

    @ResponseBody
    @RequestMapping("sys/area/provincelist")
    public Page showpro(String p_name, String c_name, Integer pageIndex) {
        Page page = new Page(5, pageIndex);
        return cityService.showProvince("%" + p_name + "%", "%" + c_name + "%", page);
    }


}

