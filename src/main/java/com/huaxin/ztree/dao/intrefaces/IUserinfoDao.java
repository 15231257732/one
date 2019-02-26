package com.huaxin.ztree.dao.intrefaces;

import java.util.List;
import com.huaxin.ztree.bean.PageBean;
import com.huaxin.ztree.bean.Question;

import com.huaxin.ztree.bases.IBaseDao;

import com.huaxin.ztree.bean.Userinfo;

public interface IUserinfoDao extends IBaseDao<Userinfo> {

	public void add(Userinfo user) throws Exception;

	public void delete(String id) throws Exception;

	public List<Userinfo> query(Userinfo user) throws Exception;

	public Userinfo toUpdate(Userinfo user) throws Exception;

	public Userinfo getUserByNameAndPassword(Userinfo user);

	List<Userinfo> pageBean(Userinfo userinfo, PageBean pageBean) throws Exception;

	public int getCount(Userinfo userinfo);
	
	public List<Userinfo> querys(Userinfo userinfo) throws Exception;

}
