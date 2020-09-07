package com.erp.dao;

import com.erp.pojo.Journal;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Date;

public interface JournalDao {

    public int Addlog(@Param("module_id") Integer module_id,
                      @Param("u_id") Integer u_id,
                      @Param("Jcontent") String Jcontent);


    ArrayList<Journal> journalList(@Param("u_id") Integer u_id,
                                   @Param("uname") String uname,
                                   @Param("jcontent") String jcontent,
                                   @Param("module_rname") String module_rname,
                                   @Param("start") String start,
                                   @Param("end") String end,
                                    @Param("pageIndex") Integer pageIndex);

    int getAlljournalList(@Param("u_id") Integer u_id,
                                   @Param("uname") String uname,
                                   @Param("jcontent") String jcontent,
                                   @Param("module_rname") String module_rname,
                                   @Param("start") String start,
                                   @Param("end") String end);

    Journal findjournal(@Param("j_id") Integer j_id);
}
