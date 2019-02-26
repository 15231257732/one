package com.huaxin.ztree.service;

import java.util.List;
import java.util.Map;

import com.huaxin.ztree.bean.PageBean;
import com.huaxin.ztree.bean.Question;

public interface IQuestionService {
	//增加数据
	public int addOrUpdate(Question question) throws Exception;
	//删除数据 
	public int delete(String id)throws Exception;
	//不分页查询所有数据
	public List<Question> query(Question question)throws Exception;
	//分页查询所有数据
	public List<Question> getAllByPage(Question question,PageBean pageBean) throws Exception ;
	//返回总记录数
	public int getCount(Question question) throws Exception;
	//修改	
	public Question toUpdate(Question question)throws Exception;
	//条件查询
	public List<Map<String,Object>> qtypeQuery(Question qu)throws Exception;
	//通过ID查询一条数据
	public Question getById(String qid) throws Exception;
}
