package com.huaxin.ztree.service.impl;

import java.sql.Timestamp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxin.ztree.bean.Paper;
import com.huaxin.ztree.bean.Question;
import com.huaxin.ztree.bean.QuestionAnswer;
import com.huaxin.ztree.bean.Userinfo;
import com.huaxin.ztree.dao.intrefaces.IPaperDao;
import com.huaxin.ztree.dao.intrefaces.IScoresDao;
import com.huaxin.ztree.service.interfaces.IPaperService;

@Service
public class PaperServiceImpl implements IPaperService {

	@Autowired
	private IPaperDao paperDao;
	@Autowired
    private IScoresDao scoresDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class, RuntimeException.class })
	public List<Paper> query(Paper paper, Userinfo userinfo) throws Exception {
		String hql="from Paper where 1=1 ";
        if("1"==userinfo.getUtype()){
            hql+=" and pcourse="+userinfo.getUcourse();
        }
        if(null!=paper.getPname()&&!"".equals(paper.getPname())){
            hql+=" and pname like '%"+paper.getPname()+"%'";
        }
        if(null!=paper.getStatus()&&!"".equals(paper.getStatus())){
            hql+=" and status="+paper.getStatus();
        }
        List<Paper> list=paperDao.query(hql);
        List<Paper> list1=new ArrayList<>();
        for(Object object:list){
            Paper paper1=(Paper)object;
            String sql="select myanswer from t_scores where pid='"+paper1.getPid()+"' and uid='"+userinfo.getUid()+"'";
            String myanswer=scoresDao.query(sql,true);
            if(null==myanswer||"".equals(myanswer)){
                list1.add(paper1);
            }
        }
        return list1;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class, RuntimeException.class })
	public Paper query(String id, boolean flags) throws Exception {
		return paperDao.query(Paper.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class, RuntimeException.class })
	public int saveOrUpdate(Paper paper) throws Exception {
		paperDao.saveOrUpdate(paper);
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class, RuntimeException.class })
	public Map<String, Object> query(Map<String, Object> map) throws Exception {
		String qcourse = (String) map.get("qcourse");
		String qstage = (String) map.get("qstage");
		int size = (map.size() - 2) / 2;
		for (int i = 1; i <= size; i++) {
			String type = (String) map.get("qtype" + i);
			Integer number = (Integer) map.get("number" + i);
			List list = paperDao.query(type, number, qcourse, qstage);
			List list2 = returnlist(list);
			map.put("list"+i, list2);
		}
		return map;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class, RuntimeException.class })
	public List query(String ids) throws Exception {
		List list = paperDao.query(ids, true);
		List list2 = returnlist(list);
		return list2;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class, RuntimeException.class })
	public void delete(Paper paper) throws Exception {
		paperDao.delete(paper);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class, RuntimeException.class })
	public List<Paper> query(Paper paper) throws Exception {
		String hql;
		if (null == paper) {
			hql = "from Paper where endtime<=" + new Timestamp(new Date().getTime());
		} else {
			hql = "update Paper u set u.status=0 where u.pid=" + paper.getPid();
		}
		return paperDao.query(hql);
	}

	private List returnlist(List list) {
		List list2 = new ArrayList();
		try {
			for (Object obj : list) {
				Map map = (Map) obj;
				Question question = new Question();
				question.setQid((String) map.get("qid"));
				question.setQanswer((String)map.get("qanswer"));
				question.setQcourse((String)map.get("qcourse"));
				question.setQname((String)map.get("qname"));
				question.setQscore((String)map.get("qscore"));
				question.setQstage((String)map.get("qstage"));
				question.setQtype((String)map.get("qtype"));
				List list1=paperDao.query(question.getQid(), true);
				List list3=new ArrayList();
				for(Object o:list1) {
					QuestionAnswer questionAnswer=new QuestionAnswer();
					Map map1=(Map)o;
					questionAnswer.setQaid((String)map1.get("qaid"));
					questionAnswer.setQansername((String)map1.get("qaname"));
					Question question1=new Question();
					question1.setQid((String)map1.get("qid"));
					questionAnswer.setQuestion(question1);
					list3.add(questionAnswer);
				}
				question.setAnswer(list3);
				list2.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list2;
	}

}
