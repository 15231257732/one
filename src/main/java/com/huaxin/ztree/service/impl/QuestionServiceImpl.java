package com.huaxin.ztree.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxin.ztree.bean.PageBean;
import com.huaxin.ztree.bean.Question;
import com.huaxin.ztree.dao.IQuestionDao;
import com.huaxin.ztree.service.IQuestionService;


@Service
public class QuestionServiceImpl implements IQuestionService {
	@Autowired
	private IQuestionDao questionDao;
	
	//增加方法
	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public int addOrUpdate(Question question) throws Exception {
		String qid=question.getQid();
		if(StringUtils.isNotEmpty(question.getQid())) {
			questionDao.toUpdate(question);
		}else {
			questionDao.addOrupdate(question);
		}
		return 0;
	}
	//删除方法
	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public int delete(String qid) throws Exception {
		return questionDao.delete(qid);
	}
	//不分页查询所有数据
	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public List<Question> query(Question question) throws Exception {
		return questionDao.query(question);
	}
	//分页查询所有数据
	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public List<Question> getAllByPage(Question question, PageBean pagebean) throws Exception {
		return questionDao.getAllByPage(question, pagebean);
	}
	//返回总记录数
	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public int getCount(Question question) throws Exception {
		return questionDao.getCount(question);
	}

	//修改
	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public Question toUpdate(Question question) throws Exception {
		return questionDao.toUpdate(question);
	}
	//根据条件查询
	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public List<Map<String, Object>> qtypeQuery(Question qu) throws Exception {
		
		return questionDao.qtypeQuery(qu);
	}
	//通过ID获取一条记录
	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public Question getById(String qid) throws Exception {
		return questionDao.getById(qid);
	}
}
