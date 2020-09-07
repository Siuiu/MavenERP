package com.erp.controller;

import com.erp.pojo.module;
import com.erp.pojo.users;
import com.erp.service.moduleService;
import com.erp.utils.Page;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ModuleController {
    @Resource
    moduleService modules;

    @ResponseBody
    @RequestMapping("/sys/modules/modulelist")
    public Page modulelist(String module_rname,Integer pageIndex){
        Page page = new Page(5, pageIndex);
        page= modules.modulelist("%"+module_rname+"%",page);
        return page;
    }

    @ResponseBody
    @RequestMapping("/sys/modules/showFatherModule")
    public ArrayList<module> showFatherModule(){
        return modules.showFatherModule();
    }

    @ResponseBody
    @RequestMapping("/sys/modules/addModule")
    public int addModule(String module_rname,String url,Integer p_id){
        return modules.addModule(module_rname,url,p_id);
    }
    @ResponseBody
    @RequestMapping("/sys/modules/module_updateShow")
    public module module_updateShow(Integer m_id){
        return modules.module_updateShow(m_id);
    }

    @ResponseBody
    @RequestMapping("/sys/modules/updateModule")
    public int updateModule(String module_rname, String url, Integer p_id, Integer m_id){
        return modules.updateModule(module_rname,url,p_id,m_id);
    }

    @ResponseBody
    @RequestMapping("/sys/modules/removeR")
    public int removeModule(Integer m_id){
        return modules.updateremoveModule(m_id);
    }

    @ResponseBody
    @RequestMapping("/sys/modules/sureR")
    public int sureModule(Integer m_id){
        return modules.updatesureModule(m_id);
    }
    @ResponseBody
    @RequestMapping("/sys/modules/findwho")
    public users findwho(HttpServletRequest req){
        users u=(users) req.getSession().getAttribute("user");
        return u;
    }


}
