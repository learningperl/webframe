package com.testing.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class report {

	public void sendreport (String resultFile,String startTime)  {
		// TODO Auto-generated method stub
		//初始化邮件配置，通过map设置
		Map<String,String> map = new HashMap<String,String>();
		map.put("email", "m18570394312_1@163.com");
		map.put("mailpass", "dongnao001");
		map.put("sendnick", "Will");
		map.put("host", "smtp.163.com");
		map.put("recievers", "1052949192@qq.com");
		map.put("copylist", "1052949192@qq.com");
		sendMail mail = new sendMail();
		mail.initMail(map);
			
		//解析结果，存到list数据结构
		ExcelResult excel = new ExcelResult();
		List<Map<String,String>> resultList = excel.Sumarry(resultFile,startTime); 
		
		//通过list生成报告内容
		String content = makeReport(resultList);
		
		//发送邮件
		try {
			mail.send(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//通过模板，生成报告内容
	public String makeReport(List<Map<String,String>> resultlist) {
		//邮件开头
		String mailstart = "<html><style type=\"text/css\">body{ background:#ffffff; margin:0 auto; padding:0; text-align:left; font-size:12px; font-family: \"微软雅黑\",\"宋体\";}table{ font-size:12px; font-family: \"微软雅黑\",\"宋体\";}.table_c{border:1px solid #ccc;border-collapse:collapse; }.table_c td{border:1px solid #ccc; border-collapse:collapse;}.table_b{border:1px solid #666;border-collapse:collapse; }.table_b td{ border-collapse:collapse; border:1px solid #ccc;}.table_b th{color:#fff; background:#666;}a:link{ color:#3366cc; font-weight:normal; }a:visited { color: #3366cc;}a:hover{ color:#000; }a:active { color:#3366cc; }td{ line-height:20px;}</style><table width=\"650\" border=\"0\" cellspacing=\"0\" align=\"center\" cellpadding=\"0\" style=\"border: #ccc 1px solid;\"><tbody><tr><td><table width=\"650\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"fbfbfb\" style=\"border-bottom: #eeeeee 1px solid;border-top: #cc0000 1px solid;\"><tbody><tr><td width=\"137\"><img src=\"Host/wp-content/themes/busiprof/images/logo.png\" width=\"137\" height=\"80\"></td><td width=\"249\" align=\"left\" style=\"font-size: 12px; color: #999999; padding-top: 26px;\">此信为自动化测试邮件，请不要直接回复。</td><td width=\"264\" align=\"left\" style=\"font-size: 12px; font-weight: bold; color: #999999; padding-top: 26px;\"><a href=\"https://ke.qq.com/course/168816?tuin=92d83d92\" target=\"_blank\"><strong><font color=\"#666666\">公开课</font></strong></a>&nbsp;&nbsp;<a href=\"https://ke.qq.com/course/172084?tuin=92d83d92\" target=\"_blank\"><strong><font color=\"#666666\">VIP课程链接</font></strong></a>&nbsp;&nbsp;<a href=\"http://wpa.qq.com/msgrd?v=3&uin=1474352943&site=qq&menu=yes\" target=\"_blank\"><strong><font color=\"#666666\">在线咨询</font></strong></a></td></tr></tbody></table><table width=\"592\" border=\"0\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin: 28px 28px 10px 28px;\"><tbody><tr><td style=\"font-size: 12px; color: #666666; padding-bottom: 6px;\">尊敬的领导,您好！</td></tr></tbody></table>&nbsp;<table width=\"592\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"><tbody><tr><td style=\"font-size: 14px; color: 333333; font-weight: bold; padding-bottom:15px;\">本测试邮件来自：<font color=\"ff6600\">DNTester01</font></td></tr><tr><td><table width=\"592\" border=\"0\" align=\"center\" class=\"table_c\" style=\"border-collapse:collapse;\"><tbody><tr><td width=\"100\" height=\"28\" align=\"center\" bgcolor=\"#FFFFFF\" style=\"border:1px solid #ccc;\">测试状态</td><td width=\"80\" height=\"28\" align=\"center\" bgcolor=\"#FFFFFF\" style=\"border:1px solid #ccc;\">通过率</td><td width=\"100\" height=\"28\" align=\"center\" bgcolor=\"#FFFFFF\" style=\"border:1px solid #ccc;\">开始时间</td><td width=\"80\" height=\"28\" align=\"center\" bgcolor=\"#FFFFFF\" style=\"border:1px solid #ccc;\">结束时间</td></tr><tr><td height=\"28\" bgcolor=\"#FFFFFF\" align=\"center\" style=\"border:1px solid #ccc;\">status</td><td height=\"28\" bgcolor=\"#FFFFFF\" align=\"center\" style=\"border:1px solid #ccc;\">passrate</td><td height=\"28\" bgcolor=\"#FFFFFF\" align=\"center\" style=\"border:1px solid #ccc;\">starttime</td><td height=\"28\" bgcolor=\"#FFFFFF\" align=\"center\" style=\"border:1px solid #ccc;\">endtime</td></tr></tbody></table></td></tr><tr><td style=\"font-size: 14px; color: 333333; font-weight: bold; padding-bottom:15px;\"><br>测试详情报表</td></tr><tr><td><table width=\"592\" border=\"0\" align=\"center\" class=\"table_c\" style=\"border-collapse:collapse;\"><tbody><tr><td width=\"100\" height=\"28\" align=\"center\" bgcolor=\"#FFFFFF\" style=\"border:1px solid #ccc;\">分组信息</td><td width=\"80\" height=\"28\" align=\"center\" bgcolor=\"#FFFFFF\" style=\"border:1px solid #ccc;\">用例总数</td><td width=\"80\" align=\"center\" bgcolor=\"#FFFFFF\" style=\"border:1px solid #ccc;\">通过数</td><td width=\"80\" align=\"center\" bgcolor=\"#FFFFFF\" style=\"border:1px solid #ccc;\">状态</td></tr>";
		String content = mailstart;
		content = content.replace("DNTester01", "Will");
		//读取list最后的内容中的信息，也就是整体执行情况，整体情况是否通过，通过passrate通过率是否为百分之百判断。
		if(resultlist.get(resultlist.size()-1).get("passrate").equals("100.0"))
			content = content.replace("status", "<span style=\"color:blue\">PASS</span>");
		else{
			content = content.replace("status", "<span style=\"color:red\">FAIL</span>");
		}
		//用结果列表list中的最后一个map即整体结果的内容替换报告开头的整体情况里的内容
		content = content.replace("passrate", resultlist.get(resultlist.size()-1).get("passrate"));
		content = content.replace("starttime", resultlist.get(resultlist.size()-1).get("starttime"));
		content = content.replace("endtime", resultlist.get(resultlist.size()-1).get("endtime"));
		//读取结果list中的内容，遍历list中第一个到倒数第二个的内容，也就是分组信息的内容
		for(int i=0; i<resultlist.size()-1;i++) {
			//通过html的tr和td的拼接，完成表格行数的添加
			content += "<tr>";
			content += "<td>"+resultlist.get(i).get("name")+"</td>";
			content += "<td>"+resultlist.get(i).get("count")+"</td>";
			content += "<td>"+resultlist.get(i).get("pass")+"</td>";
			//对Pass和fail设置不同的html样式
			if(resultlist.get(i).get("status").equals("Pass"))
				content += "<td style=\"color:blue;\">"+resultlist.get(i).get("status")+"</td>";
			else
				content += "<td style=\"color:red;\">"+resultlist.get(i).get("status")+"</td>";
			content += "</tr>";
		}
		//邮件结尾部分，结束table的拼接，以及最后的一些信息
		content += "</tbody></table></td></tr><tr></tbody></table><table width=\"592\" border=\"0\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin: 34px 28px 16px 28px; border-top: #e4e4e4 1px solid;\"><tbody><tr><td style=\"font-size: 12px; line-height: 20px; color: #999999; padding-top: 6px;\">如果您对本次邮件结果有任何疑问，请联系 <a href=\"http://wpa.qq.com/msgrd?v=3&uin=2463645074&site=qq&menu=yes\"target=\"_blank\"><font color=\"#666666\">Will</font></a> 反馈。<br>如果你在学习过程中遇到问题，您可以选择以下两种方式解决：<br>1、进入<a href=\"http://wpa.qq.com/msgrd?v=3&uin=1474352943&site=qq&menu=yes\" target=\"_blank\"><font color=\"#666666\">VIP课程</font></a>报名学习；<br>2、添加<a href=\"http://wpa.qq.com/msgrd?v=3&uin=1474352943&site=qq&menu=yes\" target=\"_blank\"><font color=\"#666666\">客服QQ</font></a>咨询课程和视频，我们将在一个工作日内回复，您可以通过QQ随时与我们联系；<br></td></tr></tbody></table></td></tr></tbody></table></body></html>";
		content = content.replaceAll("Host", "http://112.74.191.10");
		
		return content;
	}

}
