package com.huaxin.ztree.service;

import java.util.List;

import com.huaxin.ztree.bean.Ztree;



/**
*注释
*/
public interface IZtreeService {
	public List<Ztree> query(Ztree ztree) throws Exception;
}
