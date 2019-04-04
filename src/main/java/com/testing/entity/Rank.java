package com.testing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rank")
public class Rank {
	
	@Id
	@GeneratedValue(generator = "userid")
	@GenericGenerator(name = "userid", strategy = "identity")
	private Integer userid;

	public Integer getUserId() {
		return userid;
	}

	public void UserId(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "he")
	private Integer he;

	public Integer getHe() {
		return he;
	}

	public void setHe(Integer he) {
		this.he = he;
	}
	
	@Column(name = "c1")
	private Integer c1;

	public Integer getC1() {
		return c1;
	}

	public void setC1(Integer c1) {
		this.c1 = c1;
	}
	
	@Column(name = "c2")
	private Integer c2;

	public Integer getC2() {
		return c2;
	}

	public void setC2(Integer c2) {
		this.c2 = c2;
	}

}
