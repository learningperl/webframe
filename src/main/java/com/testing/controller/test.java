package com.testing.controller;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;
import com.testing.entity.User;

public class test {
public static void main(String[] args) {
	User user = new User();
	ArrayList<User> list = new ArrayList<User>();
	user.setId(1);
	user.setUserName("1111");
	list.add(user);
	String json = JSONObject.toJSONString(list);
	System.out.println(json);
	
}
}
