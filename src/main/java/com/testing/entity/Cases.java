package com.testing.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cases")
public class Cases {
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

	@Column(name = "casename")
	private String casename;

	public String getCaseName() {
		return casename;
	}
	
	public void setCaseName(String casename) {
		this.casename = casename;
	}

	@Column(name = "type")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	@Column(name = "passrate")
	private Double passrate;
	
	public Double getPassRate() {
		// TODO Auto-generated method stub
		return passrate;
	}
	
	public void setPassRate(Double passrate) {
		this.passrate = passrate;
	}
	
	@Column(name = "createtime")
	private Date createtime;

	public Date getCreateTime() {
		return createtime;
	}

	public void setCreateTime(Date createtime) {
		this.createtime = createtime;
	}

}
