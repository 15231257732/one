package com.huaxin.ztree.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxin.ztree.bean.Ztree;
import com.huaxin.ztree.dao.IZtreeDao;
import com.huaxin.ztree.service.IZtreeService;


/**
*注释
*/
@Service
public class ZtreeServiceImpl implements IZtreeService {
	@Autowired(required=true)
	private IZtreeDao ztreeDao;
	
	@Override
	@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.READ_COMMITTED,rollbackFor={Exception.class,RuntimeException.class})//标识开启事务
	public List<Ztree> query(Ztree ztree) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Ztree where pid="+ztree.getId();
		return ztreeDao.query(hql);
	}

}
