package com.huaxin.ztree.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

/*
 * 问题答案试题类  刘家旺
 */
@Entity(name="QuestionAnswer")
@Table(name="t_questionanswer",schema="group_two")
public class QuestionAnswer {
	private String qaid;   //答案ID ABCD各一个
	private String qansername; //答案内容
	private Question question;  //试题内容ABCD   与Question中的qid相匹配 关联
	@Id
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@GeneratedValue(generator="system-uuid")
	@Column(name="qaid",length=36)
	public String getQaid() {
		return qaid;
	}
	public void setQaid(String qaid) {
		this.qaid = qaid;
	}
	@Column(name="qaname")
	public String getQansername() {
		return qansername;
	}
	public void setQansername(String qansername) {
		this.qansername = qansername;
	}
	@ManyToOne(targetEntity=Question.class,cascade=CascadeType.ALL)
	@JoinColumn(name="qid")
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "QuestionAnswer [qaid=" + qaid + ", qansername=" + qansername + ", question=" + question + "]";
	}
	
	
	
}
