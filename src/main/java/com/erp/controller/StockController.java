package com.erp.controller;

import com.erp.pojo.Purchase;
import com.erp.pojo.Rk_warehouse;
import com.erp.pojo.Warehouse;
import com.erp.pojo.users;
import com.erp.service.StockService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/storage")
public class StockController {

    @Resource
    private StockService stockService;

    // 入库信息展示
    @RequestMapping("/stockList")
    @ResponseBody
    public PageInfo<Rk_warehouse> stockList(int pageNum, int pageSize, String position, String c_name, String rk_indent, String state, HttpServletRequest req){
        PageHelper.startPage(pageNum,pageSize);
        int usreId = 0;
        if ("组员".equals(position)){
            users user = (users)req.getSession().getAttribute("user");
            usreId = user.getU_id();

        }
        List<Rk_warehouse> list = stockService.stockList(usreId,c_name,rk_indent,state);
        PageInfo<Rk_warehouse> page = new PageInfo<>(list);
        return page;
    }

    // 修改入库信息
    @RequestMapping("/changeState")
    @ResponseBody
    public Integer changeState(String rk_indent,String sure){
        String state;
        if(sure.equals("sureOne")){
            state = "2";
        }else {
            state = "1";
        }
        return stockService.changeState(rk_indent,state);
    }

    @RequestMapping("/stock/findWarehouse")
    @ResponseBody
    public List<Warehouse>findWarehouse(){
        return stockService.findWarehouse();
    }

    @RequestMapping("/stock/findCgd")
    @ResponseBody
    public List<Purchase>findCgd(){
        return stockService.findCgd();
    }

    @RequestMapping("/stock/addDelivered")
    @ResponseBody
    public int addDelivered(String indent, Integer id, HttpSession session){
        users user= (users) session.getAttribute("user");
        Integer user_id=user.getU_id();
        int row=stockService.addDelivered(indent,id,user_id);
        return row;
    }
}
