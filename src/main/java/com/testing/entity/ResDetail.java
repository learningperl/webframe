package com.testing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "resdetail")
public class ResDetail {
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

	@Column(name = "resid")
	private Integer resid;

	public Integer getResId() {
		return resid;
	}

	public void setResId(Integer resid) {
		this.resid = resid;
	}

	@Column(name = "type")
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	@Column(name = "casename")
	private String casename;

	public String getCaseName() {
		return casename;
	}
	
	public void setCaseName(String casename) {
		this.casename = casename;
	}

	@Column(name = "keyword")
	private String keyword;

	public String getKeyWord() {
		return keyword;
	}

	public void setKeyWord(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "param1")
	private String param1;
	
	public String getParam1() {
		// TODO Auto-generated method stub
		return param1;
	}
	
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	
	@Column(name = "param2")
	private String param2;
	
	public String getParam2() {
		// TODO Auto-generated method stub
		return param2;
	}
	
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	
	@Column(name = "param3")
	private String param3;
	
	public String getParam3() {
		// TODO Auto-generated method stub
		return param3;
	}
	
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	
	@Column(name = "status")
	private String status;
	
	public String getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "actual")
	private String actual;
	
	public String getActual() {
		// TODO Auto-generated method stub
		return actual;
	}
	
	public void setActual(String actual) {
		this.actual = actual;
	}

}
