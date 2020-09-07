package com.erp.service;

import com.erp.dao.moduleDao;
import com.erp.pojo.module;
import com.erp.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class moduleService {
    @Resource
    moduleDao dao;

    public Page modulelist(String module_rname, Page page) {
        ArrayList<module> list = dao.modulelist(page.getStartIndex(),module_rname);
      int row= dao.getModuleCount(module_rname);
        page.setResult(list,row);
        return page;
    }

    public ArrayList<module> showFatherModule() {
        return dao.showFatherModule();
    }

    public int addModule(String module_rname,String url,Integer p_id) {
        return dao.addModule(module_rname,url,p_id);
    }

    public module module_updateShow(Integer m_id) {
        return dao.module_updateShow(m_id);
    }

    public int updateModule(String module_rname, String url, Integer p_id,Integer m_id) {
        return dao.updateModule(module_rname,url,p_id,m_id);
    }

    public int updateremoveModule(Integer m_id) {
        return dao.removeModule(m_id);
    }

    public int updatesureModule(Integer m_id) {
        return dao.sureModule(m_id);
    }
}
