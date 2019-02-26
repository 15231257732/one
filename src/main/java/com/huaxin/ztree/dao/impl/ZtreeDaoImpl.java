package com.huaxin.ztree.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.huaxin.ztree.dao.IZtreeDao;



/**
*注释
 * @param <T>
*/
@Repository
public class ZtreeDaoImpl<T> implements IZtreeDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Override
	public List<T> query(String hql) throws Exception {
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);	
		List<T> list = query.list();
		return list;
	}

}
