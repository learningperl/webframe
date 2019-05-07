package com.testing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "userinfo")
public class User {
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

	@Column(name = "nickname")
	private String nickname;

	public String getNick() {
		return nickname;
	}

	public void setNick(String nickname) {
		this.nickname = nickname;
	}
	
	@Column(name = "des")
	private String des;

	public String getDesc() {
		return des;
	}

	public void setDesc(String describe) {
		this.des = describe;
	}

	@Column(name = "username")
	private String username;

	public String getUserName() {
		return username;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}

	@Column(name = "pwd")
	private String pwd;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String password) {
		this.pwd = password;
	}

	@Column(name = "img")
	private String img;
	
	public String getImg() {
		// TODO Auto-generated method stub
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	@Column(name = "mailid")
	private Integer mailid;

	public Integer getMailId() {
		return mailid;
	}

	public void setMailId(Integer mailid) {
		this.mailid = mailid;
	}
}
