package com.erp.controller;

import com.erp.pojo.Inventory;
import com.erp.service.InventService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class InventController {
    @Resource
    private InventService inventService;

    @ResponseBody
    @RequestMapping("storage/inventory/inventlist")
    public PageInfo showinv(String c_name, String brand_name, String type_name, String product_model, Integer pageIndex) {
        PageHelper.startPage(pageIndex, 5);
        List<Inventory> list=inventService.showInvent("%" +c_name + "%", "%" + brand_name + "%","%" + type_name + "%","%" + product_model+ "%");
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
