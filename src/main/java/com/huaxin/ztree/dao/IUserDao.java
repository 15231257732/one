package com.huaxin.ztree.dao;

import java.util.List;

import com.huaxin.ztree.bean.PageBean;
import com.huaxin.ztree.bean.User;


/**
*注释
*/
public interface IUserDao {
	//登陆
	public User login(User user) throws Exception;
	//查询
	public List<User> query(User user) throws Exception;
	//添加
	public int add(User user) throws Exception;
	//删除
	public int delete(String uid) throws Exception;
	//去修改
	public User toupdate(User user)throws Exception;
	//ID查询
	public User getByid(String uid)throws Exception;
	
	
	//上下页跳转
	List<User> pageBean(User user, PageBean pageBean) throws Exception;
    //总页数
	public int getCount(User user);
    //账号和手机号查询
	public List<User> bynamequery(User user) throws Exception;
}
