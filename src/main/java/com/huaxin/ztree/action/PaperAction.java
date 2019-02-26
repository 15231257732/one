package com.huaxin.ztree.action;

import java.io.File;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaxin.ztree.bean.Paper;
import com.huaxin.ztree.bean.Userinfo;
import com.huaxin.ztree.service.interfaces.IPaperService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class PaperAction extends ActionSupport implements ModelDriven<Paper> {

	@Autowired
	private IPaperService paperService;
	
	private Integer number01;
	private Integer number02;
	private Integer number03;
	private String qstage;
	private String qcourse;
	private String result;
	
	private File filepath;
	private String filepathFileName;
	private String path="/var/source";
	
	private Paper paper=new Paper();
	
	public Integer getNumber01() {
		return number01;
	}
	public void setNumber01(Integer number01) {
		this.number01 = number01;
	}



	public Integer getNumber02() {
		return number02;
	}
	public void setNumber02(Integer number02) {
		this.number02 = number02;
	}



	public Integer getNumber03() {
		return number03;
	}
	public void setNumber03(Integer number03) {
		this.number03 = number03;
	}



	public String getQstage() {
		return qstage;
	}
	public void setQstage(String qstage) {
		this.qstage = qstage;
	}



	public String getQcourse() {
		return qcourse;
	}
	public void setQcourse(String qcourse) {
		this.qcourse = qcourse;
	}



	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}



	public File getFilepath() {
		return filepath;
	}
	public void setFilepath(File filepath) {
		this.filepath = filepath;
	}



	public String getFilepathFileName() {
		return filepathFileName;
	}
	public void setFilepathFileName(String filepathFileName) {
		this.filepathFileName = filepathFileName;
	}



	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}



	public Paper getPaper() {
		return paper;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
//---------------------------------//
	public String deleteAction() {
		try {
			paperService.delete(paper);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String queryPaperAction(){
        try {
            Userinfo userinfo=(Userinfo) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
            List<Paper> list=paperService.query(paper,userinfo);
            result=JSONArray.toJSONString(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
	
	public String queryAction() {
		Map<String,Object> map=new HashMap();
		map.put("qtype1", "1");
		map.put("qtype2", "2");
		map.put("qtype3", "3");
		map.put("number1", number01);
		map.put("number2", number02);
		map.put("number3", number03);
		map.put("qstage", qstage);
		map.put("qcourse", qcourse);
		try {
			Map<String,Object> map1=paperService.query(map);
			result=JSONObject.toJSONString(map1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String saveAction() {
		if(null!=qcourse) {
			paper.setPcourse(String.valueOf(qcourse));
		}
		if(null!=paper.getUrl()) {
			this.filepath=new File(paper.getUrl());
		}
		try {
			if(null!=paper.getUrl()) {
				File file=new File(path);
				if(!file.exists()) {
					file.mkdirs();
				}
				FileUtils.copyFile(filepath, new File(path+File.separator+filepathFileName));
				paper.setUrl(filepathFileName);
			}else {
				if(null!=paper.getPid()) {
					paper.setUrl(paperService.query(paper.getPid(),true).getUrl());
				}else {
					paper.setUrl(" ");
				}
			}
			paperService.saveOrUpdate(paper);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
//---------------------------------//

	@Override
	public Paper getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
