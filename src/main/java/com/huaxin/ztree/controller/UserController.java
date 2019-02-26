package com.huaxin.ztree.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaxin.ztree.bean.PageBean;
import com.huaxin.ztree.bean.User;
import com.huaxin.ztree.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 注释
 */
@Controller
// 多例
@Scope(value = "prototype")

public class UserController extends ActionSupport implements ModelDriven {

	private static final long serialVersionUID = -7528528156927572634L;

	@Autowired

	private IUserService userService;

	public String result;

	private User user = new User();

	@Override
	public Object getModel() {
		return user;
	}

	public String login() throws Exception {
		User u = userService.login(user);

		/*ActionContext.getContext().put("u", u);*/

		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("user", u);

		if (u != null && u.getUid() != null) {
			if (u.getUtype().equals("0")) {
				ServletActionContext.getRequest().getSession().setAttribute("user",u);//放到session作用域
				return SUCCESS;
			} else {
				return INPUT;
			}
		} else {
			return LOGIN;
		}
	}

	public String query() throws Exception {
	    //ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		List<User> list = userService.query(user);
		result=JSONObject.toJSONString(list);
		return SUCCESS;
	}

	public String add() throws Exception {
		userService.add(user);
		return SUCCESS;
	}

	public String delete() throws Exception {
		int k = userService.delete(user.getUid());
		result = JSONObject.toJSONString(k);
		return SUCCESS;
	}

	public String saveOrUpdateAction() throws Exception {
		user = userService.toupdate(user);
		/* ServletActionContext.getRequest().setAttribute("user", user); */
		return SUCCESS;
	}

	public String getByid() throws Exception {
		user = userService.getByid(user.getUid());
		// ServletActionContext.getRequest().setAttribute("user", user);
		result = JSONObject.toJSONString(user);
		return SUCCESS;
	}
	
      public String queryss() throws Exception{
		PageBean pageBean = new PageBean();
		//总记录数
		int count=userService.getCount(user);
		// 当前页数
		String spagenumber = ServletActionContext.getRequest().getParameter("pageNumber");
		int pagenumber = Integer.parseInt(spagenumber == null ? "1" : spagenumber);
		pageBean.setCount(count);// 拿到总记录数
		pageBean.setPagesize(5);// 每页记录数
		pageBean.setPageNumber(pagenumber);// 拿到当前页数
		List<User> pagelist =userService.pageBean(user, pageBean);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", pagelist);
		map.put("pageBean", pageBean);
		result = JSONArray.toJSONString(map);
		return SUCCESS;
	}
      public String bynamequery() throws Exception {
  		// ServletActionContext.getRequest().setAttribute("user", user);
  		
  		PageBean pageBean = new PageBean();
		//总记录数
		int count=userService.getCount(user);
		// 当前页数
		String spagenumber = ServletActionContext.getRequest().getParameter("pageNumber");
		int pagenumber = Integer.parseInt(spagenumber == null ? "1" : spagenumber);
		pageBean.setCount(count);// 拿到总记录数
		pageBean.setPagesize(5);// 每页记录数
		pageBean.setPageNumber(pagenumber);// 拿到当前页数
		List<User> list = userService.bynamequery(user);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageBean", pageBean);
		result=JSONObject.toJSONString(map);
  		return SUCCESS;
  	}

}
