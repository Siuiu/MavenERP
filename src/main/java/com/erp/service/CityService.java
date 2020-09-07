package com.erp.service;

import com.erp.dao.CityDao;
import com.erp.pojo.city;
import com.erp.pojo.province;
import com.erp.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class CityService {
    @Resource
    private CityDao cityDao;

    public List<city> areaList(String p_name, String c_name) {
        List<city> list = cityDao.areaList(p_name, c_name);
        return list;
    }

    public Page showProvince(String p_name, String c_name, Page page) {
        ArrayList<city> list = cityDao.showProvince(page.getStartIndex(), p_name, c_name);
        int rowCount = cityDao.getALLcity(p_name, c_name);
        page.setResult(list, rowCount);
        return page;
    }
}
