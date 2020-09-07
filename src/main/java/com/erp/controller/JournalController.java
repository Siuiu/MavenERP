package com.erp.controller;

import com.erp.pojo.Journal;
import com.erp.service.JournalService;
import com.erp.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
public class JournalController {
    @Resource
    JournalService journal;

    @ResponseBody
    @RequestMapping("/sys/logs/showjournal")
    public Page journallist(Integer u_id,String uname,String jcontent,String module_rname,String start,String end,Integer pageIndex ){
        Page page = new Page(5, pageIndex);
        page= journal.journalList(u_id,"%"+uname+"%","%"+jcontent+"%","%"+module_rname+"%",start,end,page);
        return page;
    }

    @ResponseBody
    @RequestMapping("/sys/logs/findjournal")
    public Journal findjournal(Integer j_id){

        return journal.findjournal(j_id);
    }

}
