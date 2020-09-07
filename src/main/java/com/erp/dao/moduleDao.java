package com.erp.dao;

import com.erp.pojo.module;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface moduleDao {
    ArrayList<module> modulelist(@Param("pageIndex") Integer pageIndex,@Param("module_rname") String module_rname);

    int getModuleCount(@Param("module_rname") String module_rname);

    ArrayList<module> showFatherModule();

    int addModule(@Param("module_rname") String module_rname,
                  @Param("url") String url,
                  @Param("p_id") Integer p_id);

    module module_updateShow(@Param("m_id")Integer m_id);

    int updateModule(@Param("module_rname") String module_rname,
                     @Param("url") String url,
                     @Param("p_id") Integer p_id,
                    @Param("m_id") Integer m_id);

    int removeModule(@Param("m_id")Integer m_id);

    int sureModule(@Param("m_id") Integer m_id);
}
