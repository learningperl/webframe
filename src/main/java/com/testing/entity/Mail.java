package com.testing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "mail")
public class Mail {
	
	@Id
	@GeneratedValue(generator = "id")
	@GenericGenerator(name = "id", strategy = "identity")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid")
	private Integer userid;

	public Integer getUserId() {
		return userid;
	}

	public void setUserId(Integer userid) {
		this.userid = userid;
	}
	
	@Column(name = "type")
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name = "mname")
	private String mname;

	public String getMName() {
		return mname;
	}
	
	public void setMName(String mname) {
		this.mname = mname;
	}
	
	@Column(name = "mhtml")
	private String mhtml;

	public String getMHtml() {
		return mhtml;
	}
	
	public void setMHtml(String mhtml) {
		this.mhtml = mhtml;
	}
}
