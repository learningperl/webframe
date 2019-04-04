package com.testing.controller;

import java.io.File;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;

import com.testing.common.MyLogger;

@Controller
public class InitController implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		File file1 = new File(MyLogger.class.getResource("/upload").getFile());
		String path = file1.getAbsolutePath();
		new MyLogger(path);
		MyLogger.logger.info("项目初始化完成,日志记录在tomcat log下面的：myframe.log中");
		MyLogger.logger.info(path);
	}

}
