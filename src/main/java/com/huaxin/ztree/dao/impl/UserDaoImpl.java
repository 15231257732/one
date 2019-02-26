package com.huaxin.ztree.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huaxin.ztree.bean.PageBean;
import com.huaxin.ztree.bean.User;
import com.huaxin.ztree.dao.IUserDao;

/**
 * 注释
 */

@Repository

public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;
	@Override
	public User login(User user) throws SQLException {

		
	/*	session = sessionFactory.getCurrentSession();
		String sql="select t.uid,t.uname,t.password,t.utype from User t "
				+ " where t.uname=? and t.password=? and t.utype=? ";
		Query query = session.createQuery(sql);
		query.setParameter(0, user.getUname());
		query.setParameter(1, user.getPassword());
		query.setParameter(2, user.getUtype());
		User user1=(User)query;
		return user1;*/

		session = sessionFactory.getCurrentSession();
		String hql = "from User where Uname=? and password = ? and utype = ? ";
		Query query = session.createQuery(hql).setParameter(0, user.getUname()).setParameter(1, user.getPassword())
				.setParameter(2, user.getUtype());
		User user1 = (User) query.uniqueResult();
		return user1;
	}

	@Override
	public List<User> query(User user) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();
		String hql="from User";
		Query query=session.createQuery(hql);
		List<User> list=query.list();
		return list;
	}

	@Override
	public int add(User user) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		return 0;
	}

	@Override
	public int delete(String uid) throws Exception {
		// TODO Auto-generated method stub
		session=sessionFactory.getCurrentSession();
		User st=new User();
		st.setUid(uid);
		session.delete(st);
		return 0;
	}

	//去修改
	@Override
	public User toupdate(User user) throws Exception {
		session=sessionFactory.getCurrentSession();
		/*user =(User)session.get(User.class, user.getUid());*/
		//简写的修改语句
		//session.saveOrUpdate(user);
		String hql="update User t set t.uname=?,t.password=?,t.address=?,t.telphone=? where t.uid=?";
		Query query=session.createQuery(hql);
		query.setParameter(0, user.getUname());
		query.setParameter(1, user.getPassword());
		query.setParameter(2, user.getAddress());
		query.setParameter(3, user.getTelphone());
		query.setParameter(4, user.getUid());
		query.executeUpdate();
		return user;
	}

	@Override
	public User getByid(String uid) throws Exception {
		// TODO Auto-generated method stub
		session=sessionFactory.getCurrentSession();
		User user=new User();
		user.setUid(uid);
		user =(User)session.get(User.class, user.getUid());
		//System.out.println();
		return user;
	}

	@Override
	public List<User> pageBean(User user, PageBean pageBean) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();
		String hql = "from User u where 1=1 ";

		if (StringUtils.isNoneEmpty(user.getTelphone())) {
			hql += "and u.telphone = ?";
		}
		if (StringUtils.isNoneEmpty(user.getUname())) {
			hql += "and u.uname like ?";
		}
		hql += " order by u.uid desc";
		int k = 0;
		Query querypage = session.createQuery(hql);
		
		if (StringUtils.isNotEmpty(user.getTelphone())) {
			querypage.setParameter(k++, user.getTelphone());
		}
		if (StringUtils.isNotEmpty(user.getUname())) {
			querypage.setParameter(k++, "%" + user.getUname() + "%");
		}
		
		querypage.setMaxResults(pageBean.getPagesize());
		querypage.setFirstResult(pageBean.getStartRow());
		List<User> list = querypage.list();
		return list;
	}

	@Override
	public int getCount(User user) {
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();
		String hqlcount = "select count(*) from User u where 1=1 ";
		if (StringUtils.isNoneEmpty(user.getTelphone())) {
			hqlcount += "and u.telphone = ?";
		}
		if (StringUtils.isNoneEmpty(user.getUname())) {
			hqlcount += "and u.uname like ?";
		}
		int k=0;
		Query querycount = session.createQuery(hqlcount);
		if (StringUtils.isNotEmpty(user.getTelphone())) {
			querycount.setParameter(k++, user.getTelphone());
		}
		if (StringUtils.isNotEmpty(user.getUname())) {
			querycount.setParameter(k++, "%" + user.getUname() + "%");
		}
		Integer Count = Integer.parseInt(querycount.uniqueResult().toString());
		return Count;
	}

	@Override
	public List<User> bynamequery(User user) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();
		String hql = " select u.uid as uid,u.uname as uname,u.sex as sex,u.address as address,u.telphone as telphone,"
				+ "u.utype as utype from User u where 1=1 ";
		if(StringUtils.isNoneEmpty(user.getTelphone())) {
			hql=hql+"and u.telphone = ?";
		}
		if(StringUtils.isNoneEmpty(user.getUname())) {
			hql=hql+"and u.uname like ?";
		}
		int k=0;
		Query query = session.createQuery(hql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if(StringUtils.isNotEmpty(user.getTelphone())) {
			query.setParameter(k++, user.getTelphone());
		}
		if(StringUtils.isNotEmpty(user.getUname())) {
			query.setParameter(k++, "%"+user.getUname()+"%");
		}
		List<User> list = query.list();
		return list;
	}

}
