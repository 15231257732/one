package com.huaxin.ztree.service;

import java.util.List;
import java.util.Map;

import com.huaxin.ztree.bean.Question;
import com.huaxin.ztree.bean.QuestionAnswer;

/**
 * @author: lsx  
 * @date:   2019年1月10日 上午9:00:28
 * @Description:   
 */
public interface ITestService {
	
	public List<Question> query()throws Exception;
	
	public int total(String id,String reply)throws Exception;
}
 