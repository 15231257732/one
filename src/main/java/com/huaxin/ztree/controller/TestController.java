package com.huaxin.ztree.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.huaxin.ztree.bean.Question;
import com.huaxin.ztree.bean.QuestionAnswer;
import com.huaxin.ztree.service.ITestService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * @author: lsx  
 * @date:   2019年1月10日 上午9:15:39
 * @Description:   
 */
@Controller
public class TestController extends ActionSupport implements ModelDriven{
	
	private static final long serialVersionUID = -2882425807128162026L;
	
	@Resource
	private ITestService testService;
	private Question question=new Question();
	public String qid1;
	public String qid2;
	public String qid3;
	public String qid4;
	public String qid5;
	public String answer1;
	public String answer2;
	public String answer3;
	public String answer4;
	public String answer5;
	@Override
	public Object getModel() {
		return question;
	}
	
	public String query()throws Exception{
		List<Question> test=testService.query();
		ActionContext cxt=ActionContext.getContext();
		cxt.put("list", test);
		return SUCCESS;
	}
	public String total()throws Exception{
		String id1=qid1;
		String id2=qid2;
		String id3=qid3;
		String id4=qid4;
		String id5=qid5;
		String reply1=answer1;
		String reply2=answer2;
		String reply3=answer3;
		String reply4=answer4;
		String reply5=answer5;
		int n=0;
		n+=testService.total(id1, reply1);
		n+=testService.total(id2, reply2);
		n+=testService.total(id3, reply3);
		n+=testService.total(id4, reply4);
		n+=testService.total(id5, reply5);
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("total", n);
		return SUCCESS;
	}
	
	
}
 