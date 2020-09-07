package com.erp.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class permission {
    private int comid;
    private int comrid;
    private int compid;
    private int comstate;
    private job job;
    private module module ;
    private String p_modul;
    private List<module> setmodule=new ArrayList<>();

    public List<com.erp.pojo.module> getSetmodule() {
        return setmodule;
    }

    public void setSetmodule(List<com.erp.pojo.module> setmodule) {
        this.setmodule = setmodule;
    }

    public module getModule() {
        return module;
    }

    public void setModule(module module) {
        this.module = module;
    }

    public String getP_modul() {
        return p_modul;
    }

    public void setP_modul(String p_modul) {
        this.p_modul = p_modul;
    }

    public com.erp.pojo.job getJob() {
        return job;
    }

    public void setJob(com.erp.pojo.job job) {
        this.job = job;
    }

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public int getComrid() {
        return comrid;
    }

    public void setComrid(int comrid) {
        this.comrid = comrid;
    }

    public int getCompid() {
        return compid;
    }

    public void setCompid(int compid) {
        this.compid = compid;
    }

    public int getComstate() {
        return comstate;
    }

    public void setComstate(int comstate) {
        this.comstate = comstate;
    }
}
