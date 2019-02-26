package com.huaxin.ztree.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.alibaba.fastjson.JSONArray;
import com.huaxin.ztree.bean.Ztree;
import com.huaxin.ztree.service.IZtreeService;

import com.opensymphony.xwork2.ActionSupport;



@Controller
@Scope(value="prototype")
public class ZtreeController extends ActionSupport {

	private static final long serialVersionUID = 4442907300239012780L;

    @Autowired
    private IZtreeService ztreeService;
    
    private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
    public String query(){
        try {
            List<Ztree> listroot=ztreeService.query(new Ztree());
            Iterator<Ztree> iterator=listroot.iterator();
            while(iterator.hasNext()) {
                Ztree ztree=iterator.next();
                List<Ztree> listchild=getchild(ztreeService,ztree);
                if (!listchild.isEmpty()) {
                    ztree.setChild(listchild);
                }
            }
            result= JSONArray.toJSONString(listroot);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
	private List<Ztree> getchild(IZtreeService ztreeService, Ztree ztree) {
		// TODO Auto-generated method stub
		   List<Ztree> listroot=new ArrayList<>();
	        try {
	            listroot=ztreeService.query(ztree);
	            Iterator<Ztree> iterator=listroot.iterator();
	            while (iterator.hasNext()) {
	                Ztree ztree2=iterator.next();
	                List<Ztree> listchild=getchild(ztreeService,ztree2);
	                if(listchild.isEmpty()) {
	                    return listroot;
	                }else {
	                    ztree2.setChild(listchild);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return listroot;
	}
}
