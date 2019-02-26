package com.huaxin.ztree.dao.intrefaces;

import java.util.List;

import com.huaxin.ztree.bases.IBaseDao;
import com.huaxin.ztree.bean.Scores;

public interface IScoresDao extends IBaseDao<Scores> {

	int save(Scores scores) throws Exception;

	List<Scores> query() throws Exception;

	public void delete(String id) throws Exception;

	String query(String sql, boolean flags) throws Exception;

}
