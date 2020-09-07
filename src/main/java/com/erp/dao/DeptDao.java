package com.erp.dao;

import com.erp.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface DeptDao {
    ArrayList<dept> deptlist(@Param("dept_name") String  dept_name,
                             @Param("p_id") Integer p_id,
                             @Param("c_id") Integer c_id);

    int sureR(@Param("dept_id") Integer dept_id);

    dept dept_updateshowdept(@Param("dept_id")Integer dept_id);

    List<city> showCity(Integer p_id);

    int addDept(@Param("dept_id") Integer dept_id,
                @Param("dept_name") String dept_name,
                @Param("p_id") Integer p_id,
                @Param("c_id")  Integer c_id);

    int updateDept(@Param("dept_id") Integer dept_id,
                   @Param("dept_name") String dept_name,
                   @Param("p_id") Integer p_id,
                   @Param("c_id")  Integer c_id);

    int removeR(@Param("dept_id")Integer dept_id);

    ArrayList<job> joblist(
                            @Param("pageIndex") Integer pageindex,
                             @Param("job_name") String job_name,
                             @Param("dept_id") Integer dept_id
                          );

    int addJob(@Param("job_name") String job_name,@Param("dept_id") Integer dept_id);

    int getjobCount(@Param("job_name")String job_name,@Param("dept_id") Integer dept_id);

    job job_updateShowJob(Integer job_id);

    int jobUpdate(@Param("job_id") Integer job_id,
                  @Param("job_name") String job_name,
                  @Param("dept_id") Integer dept_id);

    int removeJob(@Param("job_id") Integer job_id);

    int sureJob(@Param("job_id")Integer job_id);

   ArrayList<module> changeJobper_Show();

    ArrayList<module> findAllSunModule(@Param("p_id") Integer p_id);

    int addPer(@Param("job_id") int job_id, @Param("p_id") int p_id);

    int delePer(@Param("job_id")int job_id);

    int find_dept_id(@Param("dept_id")Integer dept_id);

    int find_dept_name(@Param("dept_name")String dept_name);

    permission findpersion(Integer job_id);
}
