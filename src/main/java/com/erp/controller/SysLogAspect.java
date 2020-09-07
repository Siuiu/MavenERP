package com.erp.controller;

import java.lang.reflect.Method;
import java.util.Date;

import com.erp.service.JournalService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import com.erp.pojo.Journal;
import com.erp.pojo.users;
import com.erp.service.JournalService;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;
@Aspect
public class SysLogAspect {
	 public Integer id=null; 	 
	 public Integer mid=33;
	 @Resource
	 JournalService journalservicer;
	 
	
	    @Pointcut("execution(* com.erp.service.*.login(..))")
	    public void loginCell(){}

	    @Pointcut("execution(* com.erp.service.*.update*(..))")
	    public void updateCell() {}

	    @Pointcut("execution(* com.erp.service.*.delete*(..))")
	    public void deleteCell() {}

	    @Pointcut("execution(* com.erp.service.*.add*(..))")
	    public void insertCell() {}


	    @AfterReturning(value = "loginCell()", argNames = "joinPoint,object", returning = "object")
	    public void loginLog(JoinPoint joinPoint, Object object) throws Throwable {
	        users user=(users)object;
	        if (user==null) {
				return;
	        }
	        if (joinPoint.getArgs() == null) {
	            return;
	        }
	        id=user.getU_id();
	       
	        String methodName = joinPoint.getSignature().getName();
	        
	       // String opContent = optionContent(joinPoint.getArgs(), methodName);
            Journal journal=new Journal();
            journal.setJcontent("用户登录");
            journal.setU_id(user.getU_id());
            journal.setModule_id(33);
            journalservicer.Addlog(journal);
	       
	    }
	    /**
	     * 
	     *
	     * @param joinPoint
	     * @param object
	     */
	    @AfterReturning(value = "insertCell()", argNames = "joinPoint,object", returning = "object")
	    public void insertLog(JoinPoint joinPoint, Object object) throws Throwable {
	      
	        if (joinPoint.getArgs() == null) {
	        }
	        
	        String methodName = joinPoint.getSignature().getName();
	        
	        //String opContent = optionContent(joinPoint.getArgs(), methodName);
	        Journal journal=new Journal();
            journal.setJcontent("进行了添加"+getMethodChineseName(methodName)+"操作");
            journal.setU_id(id);
            journal.setModule_id(mid);
            journalservicer.Addlog(journal);
	       
	    }
	    /**
	     * 
	     *
	     * @param joinPoint
	     * @param object
	     * @throws Throwable
	     */
	    @AfterReturning(value = "updateCell()", argNames = "joinPoint,object", returning = "object")
	    public void updateLog(JoinPoint joinPoint, Object object) throws Throwable {

	        // 
	        if (joinPoint.getArgs() == null) {// 
	            return;
	        }
	        //
	        String methodName = joinPoint.getSignature().getName();
	        // 
	        //String opContent = optionContent(joinPoint.getArgs(), methodName);

	        // 
	        Journal journal=new Journal();
            journal.setJcontent("进行了修改"+getMethodChineseName(methodName)+"操作");
            journal.setU_id(id);
            journal.setModule_id(mid);
            journalservicer.Addlog(journal);
	       
	    }
	    /**
	     * 
	     *
	     * @param joinPoint
	     * @param object
	     */
	    @AfterReturning(value = "deleteCell()", argNames = "joinPoint,object", returning = "object")
	    public void deleteLog(JoinPoint joinPoint, Object object) throws Throwable {
	        // Admin admin=(Admin)
	        // request.getSession().getAttribute("businessAdmin");
	        
	        if (joinPoint.getArgs() == null) {// 
	            return;
	        }
	        
	        String methodName = joinPoint.getSignature().getName();
	       
	        //String opContent = optionContent(joinPoint.getArgs(), methodName);
	        Journal journal=new Journal();
            journal.setJcontent("进行了删除"+getMethodChineseName(methodName)+"操作");
            journal.setU_id(id);
            journal.setModule_id(mid);
            journalservicer.Addlog(journal);

	    }

	    public String getMethodChineseName(String methodName){
	        if(methodName.endsWith("custom")){
	        	mid=9;
	            return "客户信息";
	        }/*else if(methodName.endsWith("")){
	        	
	            return "不严谨的方法";
	        }*/else if(methodName.endsWith("Status")){
	        	//mid=3;
	        	mid=9;
	            return "订单状态";
	        }
			else if(methodName.endsWith("whorder")){
	        	//mid=2;
	        	mid=12;
	            return "仓库状态";
	        }
			else if(methodName.endsWith("user")){
	        	mid=2;
	            return "员工信息";
	        }
			else if(methodName.endsWith("Dept")){
	        	mid=3;
	            return "部门信息";
	        }
			else if(methodName.endsWith("Order")){
	        	//mid=1;
	        	mid=11;
	            return "订购单管理";
	        }else if(methodName.endsWith("Firm")){
	        	//mid=3;
	        	mid=25;
	            return "厂商";
	        }else if(methodName.endsWith("Job")){
	        	mid=4;
	            return "职位";
	        }
			else if(methodName.endsWith("Product")){	        	
	        	//mid=3;
	        	mid=24;
	            return "商品";
	        }else if(methodName.endsWith("Type")){
	        	//mid=3;
	        	mid=23;
	            return "商品类型";
	        }else if(methodName.endsWith("ware")){
	        	//mid=2;
	        	mid=10;
	            return "仓库";
	        }else if(methodName.endsWith("sta")){
	        	//mid=2;
	        	mid=10;
	            return "仓库状态";
	        }else if(methodName.endsWith("ckware")){
	        	//mid=2;
	        	mid=12;
	            return "出库订单";
	        }else if(methodName.endsWith("CheckStatus")){
	        	mid=9;
	            return "订购单状态";
	        }else if(methodName.endsWith("Order")){
	        	mid=11;
	            return "订单";
	        }
			else if(methodName.endsWith("Purchase")){
	        	mid=20;
	            return "采购单";
	        }
			else if(methodName.endsWith("rk")){
	        	mid=13;
	            return "入库";
	        }else if(methodName.endsWith("Details")){
	        	mid=8;
	            return "订购单";
	        }else if(methodName.endsWith("khStatus")){
	        	mid=6;
	            return "客户状态";
	        }else if(methodName.endsWith("Warehouse")){
	        	mid=14;
	            return "仓库";
	        }else if(methodName.endsWith("Service")){
	        	mid=6;
	            return "服务";
	        }else if(methodName.endsWith("Brand")){
	        	mid=22;
	            return "品牌";
	        }else if(methodName.endsWith("Module")) {
	        	mid=5;
	        	return "模块";
	        }
			else if(methodName.endsWith("pur")) {
	        	mid=15;
	        	return "采购单";
	        }else {
	            return "还没修改的方法";
	        }
	    }
	
	
}
