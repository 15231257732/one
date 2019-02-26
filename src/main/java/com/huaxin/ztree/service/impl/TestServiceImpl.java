package com.huaxin.ztree.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxin.ztree.bean.Question;
import com.huaxin.ztree.bean.QuestionAnswer;
import com.huaxin.ztree.dao.ITestDao;
import com.huaxin.ztree.service.ITestService;

/**
 * @author: lsx  
 * @date:   2019年1月10日 上午9:03:30
 * @Description:   
 */
@Service
public class TestServiceImpl implements ITestService{
	
	@Autowired
	private ITestDao testDao;
	
	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public List<Question> query() throws Exception {
		List<Question> list=testDao.query();
		for(Question question:list) {
			List<QuestionAnswer> answerlist=testDao.query2(question.getQid());
			question.setAnswer(answerlist);
		}
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.NEVER,isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})
	public int total(String id,String reply) throws Exception {
		int n=testDao.total(id, reply);
		return n;
	}

}
 