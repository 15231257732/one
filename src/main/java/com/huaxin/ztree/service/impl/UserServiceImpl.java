package com.huaxin.ztree.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxin.ztree.bean.PageBean;
import com.huaxin.ztree.bean.User;
import com.huaxin.ztree.dao.IUserDao;
import com.huaxin.ztree.service.IUserService;

/**
*注释
*/
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})//标识开启事务
	public User login(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public List<User> query(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.query(user);
	}

	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public int add(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.add(user);
	}

	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public int delete(String uid) throws Exception {
		// TODO Auto-generated method stub
		return userDao.delete(uid);
	}

	//去修改
	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public User toupdate(User user) throws Exception {
		return userDao.toupdate(user);
	}

	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public User getByid(String uid) throws Exception {
		// TODO Auto-generated method stub
		return userDao.getByid(uid);
	}

	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public List<User> pageBean(User user, PageBean pageBean) throws Exception {
		// TODO Auto-generated method stub
		return userDao.pageBean(user, pageBean);
	}

	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public int getCount(User user) {
		// TODO Auto-generated method stub
		return userDao.getCount(user);
	}

	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public List<User> bynamequery(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.bynamequery(user);
	}

}
