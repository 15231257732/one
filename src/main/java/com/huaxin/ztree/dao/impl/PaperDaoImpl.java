package com.huaxin.ztree.dao.impl;

import java.util.List;

import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.huaxin.ztree.bases.BaseDao;
import com.huaxin.ztree.bean.Paper;
import com.huaxin.ztree.bean.Question;
import com.huaxin.ztree.dao.intrefaces.IPaperDao;

@Repository
public class PaperDaoImpl extends BaseDao<Paper> implements IPaperDao {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	@Override
	public List query(String type, Integer number, String qcourse, String qstage) throws Exception {
		session = sessionFactory.getCurrentSession();
		List list;
		String sql = "select * from t_question t where 1=1";
		if (qcourse != null && !"".equals(qcourse)) {
			sql += " and t.qcourse=?";
		}
		if (qstage != null && !"".equals(qstage)) {
			sql += " and t.qstage=?";
		}
		if (type != null && !"".equals(type)) {
			sql += " and t.qtype=?";
		}
		sql += "order by rand() limit ?";
		SQLQuery query = session.createSQLQuery(sql);
		int i = 0;
		if (qcourse != null && !"".equals(qcourse)) {
			query.setParameter(i++, qcourse);
		}
		if (qstage != null && !"".equals(qstage)) {
			query.setParameter(i++, qstage);
		}
		if (type != null && !"".equals(type)) {
			query.setParameter(i++, type);
		}
		query.setParameter(i++, number);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//
		list = query.list();//
		return list;
	}

	@Override
	public List query(Integer id, boolean flags) throws Exception {
		session = sessionFactory.getCurrentSession();
		List list;
		String sql = "select qaid as qaid,qaname as qaname,qid as qid from t_questionanswer where qid=? order by qaname";
		SQLQuery query = session.createSQLQuery(sql);
		int i = 0;
		query.setParameter(i++, id);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		return list;
	}

	@Override
	public List<Question> query(String qids, boolean flags) throws Exception {
		session = sessionFactory.getCurrentSession();
		List list;
		if (flags) {
			String sql = "select * from t_question where qid in ('" + qids + "')";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = query.list();
		} else {
			SQLQuery query = session.createSQLQuery(qids);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = query.list();
		}
		return list;
	}

	@Override
	public List<Paper> query(String sql, Integer flags) throws Exception {
		session = sessionFactory.getCurrentSession();
		List list;
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		return list;
	}

}
