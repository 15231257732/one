package com.huaxin.ztree.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.omg.CORBA.TRANSACTION_UNAVAILABLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huaxin.ztree.bean.Question;
import com.huaxin.ztree.bean.QuestionAnswer;
import com.huaxin.ztree.dao.ITestDao;

/**
 * @author: lsx  
 * @date:   2019年1月9日 下午5:19:04
 * @Description:   
 */
@Repository
public class TestDaoImpl implements ITestDao{
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Override
	public List<Question> query() throws Exception {
		session=sessionFactory.getCurrentSession();
		String sql="SELECT q.qid as qid,q.qname as qname,"
				+ " q.qtype as qtype,q.qcourse as qcourse,"
				+ " q.qanswer as qanswer "
				+ " FROM t_question q "
				+ " ORDER BY RAND() LIMIT 5";
		Query q=session.createSQLQuery(sql);
		q.setResultTransformer(Transformers.aliasToBean(Question.class));
		List<Question> list=q.list();
		return list;
	}
	@Override
	public List<QuestionAnswer> query2(String id) throws Exception {
		session=sessionFactory.getCurrentSession();
		String sql="select qid as qaid,qaname as qansername from t_questionanswer where qid=?";
		Query q=session.createSQLQuery(sql);
		q.setParameter(0, id);
		q.setResultTransformer(Transformers.aliasToBean(QuestionAnswer.class));
		List<QuestionAnswer> list=q.list();
		return list;
	}
	@Override
	public int total(String id, String reply) throws Exception {
		session=sessionFactory.getCurrentSession();
		String sql="SELECT qid as qid,qanswer as qanswer FROM t_question where qid=? and qanswer=?";
		Query q=session.createSQLQuery(sql);
		q.setParameter(0, id);
		q.setParameter(1, reply);
		q.setResultTransformer(Transformers.aliasToBean(Question.class));
		List<Question> list=q.list();
		return 20;
	}
	
		
	
}
 