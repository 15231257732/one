package com.huaxin.ztree.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.huaxin.ztree.bean.PageBean;
import com.huaxin.ztree.bean.Question;
import com.huaxin.ztree.bean.QuestionAnswer;
import com.huaxin.ztree.service.IQuestionService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class QuestionController extends ActionSupport implements ModelDriven{
	@Autowired
	private IQuestionService questionService;
	private Question question=new Question();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8271667772574760260L;
	@Override
	public Object getModel() {
		return question;
	}
	//增加数据到数据库
	public String addOrUpdate() {
		try {
			String st=ServletActionContext.getRequest().getParameter("qtype");
			int t=Integer.parseInt(st);
			if(t==2) {
				//多选
				String[] strings =ServletActionContext.getRequest().getParameterValues("qaname");
				String[] strings2 =ServletActionContext.getRequest().getParameterValues("qanswer1");
				List<QuestionAnswer> list=new ArrayList<>();
				for(int i=0;i<strings2.length;i++) {
					QuestionAnswer question=new QuestionAnswer();
					question.setQansername(strings2[i]+strings[i]);
					list.add(question);
				}
				question.setAnswer(list);
				questionService.addOrUpdate(question);
			}else if(t==1) {
				//单选
				String[] strings =ServletActionContext.getRequest().getParameterValues("qaname1");
				String[] strings2 =ServletActionContext.getRequest().getParameterValues("qanswer2");
				List<QuestionAnswer> list=new ArrayList<>();
				for(int i=0;i<strings2.length;i++) {
					QuestionAnswer question=new QuestionAnswer();
					question.setQansername(strings2[i]+strings[i]);
					list.add(question);
				}
				question.setAnswer(list);
				questionService.addOrUpdate(question);
			}else {
				//判断对错
				String[] strings =ServletActionContext.getRequest().getParameterValues("qaname2");
				String[] strings2 =ServletActionContext.getRequest().getParameterValues("qanswer3");
				
				List<QuestionAnswer> list=new ArrayList<>();
				for(int i=0;i<strings2.length;i++) {
					QuestionAnswer question=new QuestionAnswer();
					question.setQansername(strings2[i]+strings[i]);
					list.add(question);
				}
				question.setAnswer(list);
				questionService.addOrUpdate(question);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	} 
	//删除数据
	public String delete() throws Exception{
		questionService.delete(question.getQid());
		return SUCCESS;
	}
	//不分页查询所有数据
	public String query() throws Exception{
		List<Question> list=questionService.query(question);
		ServletActionContext.getRequest().setAttribute("list", list);
		return SUCCESS;
	}
	//分页查询所有记录数
	public String Query() throws Exception{
		PageBean pagebean= new PageBean();
		//总页数
		int count=questionService.getCount(question);
		//当前页数
		String spagenumber=ServletActionContext.getRequest().getParameter("pageNumber");
		int pagenumber=Integer.parseInt(spagenumber==null?"1":spagenumber);
		pagebean.setCount(count);//总记录数
		pagebean.setPagesize(8);//每页显示的数
		pagebean.setPageNumber(pagenumber);//拿到当前页数
		List<Question> pagelist= questionService.getAllByPage(question, pagebean);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", pagelist);
		map.put("pageBean", pagebean);
		map.put("PageCount", pagebean.getPageCount());
		ServletActionContext.getRequest().setAttribute("map",map);
		return SUCCESS;
	}
		
	//修改的方法
	public String toUpdate() throws Exception{
		String qid = ServletActionContext.getRequest().getParameter("qid");
		question=questionService.getById(qid);
		ServletActionContext.getRequest().setAttribute("question", question);
		return SUCCESS;
	}
	//条件查询
	/*qcourse   学科专业  Qtype   问题类型   qstage   学科阶段*/
	public String qtypeQuery()throws Exception{
		String qtype=question.getQtype();
		String qcourse=question.getQcourse();
		String qstage=question.getQstage();
		Question u=new Question();
		u.setQtype(qtype);
		u.setQcourse(qcourse);
		u.setQstage(qstage);
		List<Map<String,Object>> list=questionService.qtypeQuery(u);
		ServletActionContext.getRequest().setAttribute("list", list);
		return SUCCESS;
	}
}
