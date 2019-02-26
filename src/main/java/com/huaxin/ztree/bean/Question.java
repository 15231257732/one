package com.huaxin.ztree.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;





/*
 * 题目 实体类  刘家旺
 */
@Entity
//name 表名称  schema数据库名称
@Table(name="t_question",schema="group_two")
public class Question {
	private String qid;	//问题id
    private String qname;	//题目描述
    private String qtype;	//题目类型
    private String qanswer;	//问题答案
    private String qscore;	//问题分值
    private String qstage;	//阶段类别
    private String qcourse;	//学科专业
    private List<QuestionAnswer> answer; //问题的备选项目  
    
    @OneToMany(targetEntity=QuestionAnswer.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="qid")
    public List<QuestionAnswer> getAnswer() {
		return answer;
	}
	
	public void setAnswer(List<QuestionAnswer> answer) {
		this.answer = answer;
	}

	@Id
    @GenericGenerator(name="system-uuid",strategy="uuid")
//	下面我的实体类的代码，我把@GeneratedValue注释掉之后就不会出现这个问题了。
//	具体是因为mysql里面id已经是设置为自动增长了，而这里又用注解设置，所以导致数据库操作失败了。
    @GeneratedValue(generator="system-uuid")
    @Column(name="qid",length=36)
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	@Column(name="qname",length=50)
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	@Column(name="qtype",length=40)
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	@Column(name="qanswer",length=40)
	public String getQanswer() {
		return qanswer;
	}
	public void setQanswer(String qanswer) {
		this.qanswer = qanswer;
	}
	@Column(name="qscore",length=40)
	public String getQscore() {
		return qscore;
	}
	public void setQscore(String qscore) {
		this.qscore = qscore;
	}
	@Column(name="qstage",length=40)
	public String getQstage() {
		return qstage;
	}
	public void setQstage(String qstage) {
		this.qstage = qstage;
	}
	@Column(name="qcourse",length=40)
	public String getQcourse() {
		return qcourse;
	}
	public void setQcourse(String qcourse) {
		this.qcourse = qcourse;
	}

	@Override
	public String toString() {
		return "Question [qid=" + qid + ", qname=" + qname + ", qtype=" + qtype + ", qanswer=" + qanswer + ", qscore="
				+ qscore + ", qstage=" + qstage + ", qcourse=" + qcourse + ", answer=" + answer + "]";
	}
	
	
}
