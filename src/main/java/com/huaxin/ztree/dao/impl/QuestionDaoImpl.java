package com.huaxin.ztree.dao.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huaxin.ztree.bean.PageBean;
import com.huaxin.ztree.bean.Question;
import com.huaxin.ztree.dao.IQuestionDao;

@Repository
public class QuestionDaoImpl implements IQuestionDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	
	//增加数据
	@Override
	public int  addOrupdate(Question question) throws Exception {
		session=sessionFactory.getCurrentSession();
		session.save(question);
		return 0;
	}
	//删除数据
	@Override
	public int delete(String id) throws Exception {
		session=sessionFactory.getCurrentSession();
		Question question=new Question();
		question.setQid(id);
		session.delete(question);
		return 0;
	}
	//不分页查询所有数据数据
	@Override
	public List<Question> query(Question question) throws Exception {
		session=sessionFactory.getCurrentSession();
		String hql="from Question ";
		org.hibernate.Query query= session.createQuery(hql);
		List<Question>list=query.list();
		return list;
	}
	//分页查询所有数据
	@Override
	public List<Question> getAllByPage(Question question, PageBean pagebean) throws Exception {
		session=sessionFactory.getCurrentSession();
		String hql="from Question where 1=1";
		if(question.getQcourse()!=null) {
			hql+=" and qcourse =?";
		}
		if(StringUtils.isNotEmpty(question.getQtype())) {
			hql+=" and qtype =?";
		}
		if(question.getQstage()!=null) {
			hql+=" and qstage =?";
		}
		int k=0;
		Query query=session.createQuery(hql);
		
		if(question.getQcourse()!=null) {
			query.setParameter(k++, question.getQcourse());
		}
		if(StringUtils.isNotEmpty(question.getQtype())){
			query.setParameter(k++, question.getQtype());
		}
		if(question.getQstage()!=null) {
			query.setParameter(k++, question.getQstage());
		}
		query.setMaxResults(pagebean.getPagesize());
		query.setFirstResult(pagebean.getStartRow());
		List<Question> list=query.list();
		return list;
	}
	//返回总记录数
	@Override
	public int getCount(Question question) throws Exception {
		session=sessionFactory.getCurrentSession();
		String hql="select count(*) from Question where 1=1";
		if(question.getQcourse()!=null) {
			hql+=" and qcourse =?";
		}
		if(StringUtils.isNotEmpty(question.getQtype())) {
			hql+=" and qtype =?";
		}
		if(question.getQstage()!=null) {
			hql+=" and qstage =?";
		}
		int k=0;
		Query query=session.createQuery(hql);
		
		if(question.getQcourse()!=null) {
			query.setParameter(k++, question.getQcourse());
		}
		if(StringUtils.isNotEmpty(question.getQtype())) {
			query.setParameter(k++, question.getQtype());
		}
		if(question.getQstage()!=null) {
			query.setParameter(k++, question.getQstage());
		}
		int count = ((Long) query.iterate().next()).intValue();
		return count;
	}

	//修改
	@Override
	public Question toUpdate(Question question) throws Exception {
			session=sessionFactory.getCurrentSession();
			session.update(question);
			return question ;
	}
	//根据查询数据
	@Override
	public List<Map<String,Object>> qtypeQuery(Question qu) throws Exception {
		session=sessionFactory.getCurrentSession();
		String hql="select qid as qid,qanswer as qanswer,"
				+ "qcourse as qcourse,qname as qname,"
				+ "qscore as qscore,qstage as qstage,qtype as qtype "
				+ "from Question "
				+ "where 1=1 ";
		/*Qtype   问题类型*/
		if(qu.getQtype()!=null && !"".equals(qu.getQtype())){
			hql+=" and qtype =?";
		} 
		/*qcourse   学科专业*/
		if(qu.getQcourse()!=null && !"".equals(qu.getQcourse())){
			hql+=" and qcourse =?";
		}
		/*qstage   学科阶段*/
		if(qu.getQstage()!=null && !"".equals(qu.getQstage())){
			hql+=" and qstage =?";
		}
		//
		int k=0;
		org.hibernate.Query q=session.createQuery(hql);
		if(qu.getQtype()!=null && !"".equals(qu.getQtype())){
			q.setParameter(k++, qu.getQtype());
		}
		if(qu.getQcourse()!=null && !"".equals(qu.getQcourse())){
			q.setParameter(k++, qu.getQcourse());
		}
		if(qu.getQstage()!=null && !"".equals(qu.getQstage())){
			q.setParameter(k++, qu.getQstage());
		}
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> question=q.list();
		return question;
	}
	//通过ID查询一条数据
	@Override
	public Question getById(String qid) throws Exception {
		session=sessionFactory.getCurrentSession();
		String hql="from Question where qid=?";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameter(0, qid);
		Question user = (Question)query.uniqueResult();
		return user;
	}
}
