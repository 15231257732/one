package com.huaxin.ztree.dao.intrefaces;

import java.util.List;


import com.huaxin.ztree.bases.IBaseDao;
import com.huaxin.ztree.bean.Paper;
import com.huaxin.ztree.bean.Question;

public interface IPaperDao extends IBaseDao<Paper> {

	List query(String type, Integer number, String qcourse, String qstage) throws Exception;

	List query(Integer id, boolean flags) throws Exception;

	List<Question> query(String qids, boolean flags) throws Exception;

	List<Paper> query(String sql, Integer flags) throws Exception;
}
