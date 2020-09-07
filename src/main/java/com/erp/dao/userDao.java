package com.erp.dao;

import com.erp.pojo.dept;
import com.erp.pojo.job;
import com.erp.pojo.module;
import com.erp.pojo.users;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface userDao {
    users userlogin(@Param("uname") String uname,@Param("upassword") String upasword);

    users findper(@Param("uname") String uname);

    module  findmoduleurl(@Param("module_pid")int id);

    ArrayList<users> userlist(@Param("startIndex")int startIndex,@Param("u_id") Integer id,@Param("uname")String uname,@Param("daptname")String deptname,@Param("state") Integer state);

    int getEmpUserCount(@Param("u_id") Integer id,@Param("uname")String uname,@Param("daptname")String deptname,@Param("state") Integer state);

    ArrayList<dept> deptlist();

    users testUid(@Param("u_id") int u_id);

    users testUname(@Param("uname") String uname);
    ArrayList<job> findjob(@Param("dept_id") int dept_id);
    int saveUser(@Param("u_id") int u_id,
                 @Param("uname") String uname,
                 @Param("upassword") String upassword,
                 @Param("utelephone") String utelephone,
                 @Param("dept_id") int dept_id,
                 @Param("job_id") int job_id,
                 @Param("sex") String sex,
                 @Param("birthday") String birthday);

    int updateStatus(@Param("u_id") int u_id);

    users update_showuser(Integer u_id);

    int updateuser(@Param("u_id") int u_id,
                   @Param("upassword") String upassword,
                   @Param("utelephone")  String utelephone,
                   @Param("dept_id")  int dept_id,
                   @Param("job_id")  int job_id,
                   @Param("sex") String sex,
                   @Param("birthday")  String birthday,
                   @Param("status_id")  int status_id);

    int updateSureStatus(@Param("u_id")Integer u_id);
}
