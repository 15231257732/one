package com.huaxin.ztree.dao;

import java.util.List;
import java.util.Map;

import com.huaxin.ztree.bean.Question;
import com.huaxin.ztree.bean.QuestionAnswer;

/**
 * @author: lsx  
 * @date:   2019年1月9日 下午5:16:31
 * @Description:   
 */
public interface ITestDao {
	
	public List<Question> query()throws Exception;
	
	public List<QuestionAnswer> query2(String id) throws Exception;
	
	public int total(String id,String reply)throws Exception;
}
 