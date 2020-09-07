package com.erp.service;

import com.erp.dao.JournalDao;
import com.erp.pojo.Journal;
import com.erp.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class JournalService {
    @Resource
    JournalDao dao;
    public int Addlog(Journal journal){
        return dao.Addlog(journal.getModule_id(),journal.getU_id(),journal.getJcontent());
    }

    public Page journalList(Integer u_id, String uname, String jcontent, String module_rname, String start, String end, Page page ) {

        ArrayList<Journal> list = dao.journalList(u_id, uname, jcontent, module_rname, start, end,page.getStartIndex());
        int rowCount=dao.getAlljournalList(u_id, uname, jcontent, module_rname, start, end);
        page.setResult(list,rowCount);
       return page;
    }

    public Journal findjournal(Integer j_id) {
        return dao.findjournal(j_id);
    }
}
