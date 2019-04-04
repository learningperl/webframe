package com.testing.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "res")
public class Res {
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
	
	@Column(name = "caseid")
	private Integer caseid;

	public Integer getCaseId() {
		return caseid;
	}

	public void setCaseId(Integer caseid) {
		this.caseid = caseid;
	}
	
	@Column(name = "resname")
	private String resname;

	public String getResName() {
		return resname;
	}

	public void setResName(String resname) {
		this.resname = resname;
	}
	
	@Column(name = "type")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "reportname")
	private String reportname;

	public String getReportName() {
		return reportname;
	}

	public void setReportName(String reportname) {
		this.reportname = reportname;
	}

	@Column(name = "casecount")
	private Integer casecount;

	public Integer getCount() {
		return casecount;
	}

	public void setCount(Integer casecount) {
		this.casecount = casecount;
	}

	@Column(name = "pass")
	private Integer pass;

	public Integer getPass() {
		return pass;
	}

	public void setPass(Integer pass) {
		this.pass = pass;
	}
	
	@Column(name = "fail")
	private Integer fail;

	public Integer getFail() {
		return fail;
	}

	public void setFail(Integer fail) {
		this.fail = fail;
	}
	
	@Column(name = "starttime")
	private Date starttime;

	public Date getStartTime() {
		return starttime;
	}

	public void setStartTime(Date starttime) {
		this.starttime = starttime;
	}
	
	@Column(name = "endtime")
	private Date endtime;

	public Date getEndTime() {
		return endtime;
	}

	public void setEndTime(Date endtime) {
		this.endtime = endtime;
	}
	
	@Column(name = "status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
