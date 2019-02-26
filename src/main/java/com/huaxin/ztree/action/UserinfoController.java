package com.huaxin.ztree.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.huaxin.ztree.bean.PageBean;
import com.huaxin.ztree.bean.Userinfo;
import com.huaxin.ztree.service.interfaces.IUserinfoService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class UserinfoController extends ActionSupport implements ModelDriven {
	private static final long serialVersionUID = -1178782298909411607L;
	@Autowired
	private IUserinfoService studentService;

	private Userinfo userinfo = new Userinfo();
	private String result;
	
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return userinfo;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	public String addDefaultUserAction(){
		try {
			int n=studentService.getCount(userinfo);
			if(n==0){
				userinfo.setUtype("0");
				userinfo.setAddress("admin");
				userinfo.setPassword("admin");
				userinfo.setSex("男");
				userinfo.setTelphone("11111111111");
				userinfo.setUcourse("0");
				userinfo.setUname("admin");
				studentService.add(userinfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String add() throws Exception {
		userinfo.setUtype("1");
		studentService.add(userinfo);
		return SUCCESS;
	}
	public String delete() throws Exception {
		studentService.delete(userinfo.getUid());
		return SUCCESS;
	}
	public String query() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		String name=request.getParameter("txtName");
		userinfo.setUname(name);
		List<Userinfo> list = studentService.query(userinfo);
		request.setAttribute("list", list);
		return SUCCESS;
	}
	public String toUpdate() throws Exception {
		userinfo = studentService.toUpdate(userinfo);
		ServletActionContext.getRequest().setAttribute("user", userinfo);
		return SUCCESS;
	}
	public String login() {
		String utype = userinfo.getUtype();
		userinfo = studentService.getUserByNameAndPassword(userinfo);
		if (null != userinfo && !"".equals(userinfo)) {
			ServletActionContext.getRequest().setAttribute("userinfo", userinfo);
			ServletActionContext.getRequest().getSession().setAttribute("userinfo", userinfo);
            if("1".equals(userinfo.getUtype())) {
                return "Loginsucceed";
            }else{
                return "AdminSucceed";
            }
		} else {
			return "LoginFaile";
		}
	}
	public String querys() {
		try {
			PageBean pageBean = new PageBean();
			// 总记录数
			int count = studentService.getCount(userinfo);
			// 当前页数
			String spagenumber = ServletActionContext.getRequest().getParameter("pageNumber");
			int pagenumber = Integer.parseInt(spagenumber == null ? "1" : spagenumber);
			pageBean.setCount(count);// 拿到总记录数
			pageBean.setPagesize(5);// 每页记录数
			pageBean.setPageNumber(pagenumber);// 拿到当前页数
			List<Userinfo> pagelist = studentService.pageBean(userinfo, pageBean);//null
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", pagelist);
			map.put("pageBean", pageBean);
			result = JSONArray.toJSONString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
}
