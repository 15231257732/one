package com.huaxin.ztree.dao;

import java.util.List;

/**
*注释
*/
public interface IZtreeDao<T>{
	//菜单树
	public List<T> query(String hql) throws Exception;

}
