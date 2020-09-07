package com.erp.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class module {
    private int module_id;
    private String module_rname;
    private int module_pid;
    private String module_murl;
    private String module_state;
    private List<module> setmodel=new ArrayList<module>();
    private String  p_name;

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public List<module> getSetmodel() {
        return setmodel;
    }

    public void setSetmodel(List<module> setmodel) {
        this.setmodel = setmodel;
    }

    public String getModule_murl() {
        return module_murl;
    }

    public void setModule_murl(String module_murl) {
        this.module_murl = module_murl;
    }


    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public String getModule_rname() {
        return module_rname;
    }

    public void setModule_rname(String module_rname) {
        this.module_rname = module_rname;
    }

    public int getModule_pid() {
        return module_pid;
    }

    public void setModule_pid(int module_pid) {
        this.module_pid = module_pid;
    }



    public String getModule_state() {
        return module_state;
    }

    public void setModule_state(String module_state) {
        this.module_state = module_state;
    }
}
