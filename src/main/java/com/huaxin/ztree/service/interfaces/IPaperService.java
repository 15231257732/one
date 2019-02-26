package com.huaxin.ztree.service.interfaces;

import java.util.List;
import java.util.Map;

import com.huaxin.ztree.bean.Paper;
import com.huaxin.ztree.bean.Userinfo;



public interface IPaperService  {

List<Paper> query(Paper paper,Userinfo userinfo)throws Exception;
	
	Paper query(String id,boolean flags)throws Exception;
	
	int saveOrUpdate(Paper paper)throws Exception;
	
	Map<String,Object> query(Map<String,Object> map)throws Exception;
	
	List query(String ids)throws Exception;
	
	void delete(Paper paper)throws Exception;
	
	List<Paper> query(Paper paper)throws Exception;
}
