package com.testing.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.common.ExcelReader;
import com.testing.common.MyLogger;
import com.testing.entity.CaseDetail;
import com.testing.entity.Cases;
import com.testing.entity.Res;
import com.testing.entity.ResDetail;

@Service("uploadService")
public class UploadManager implements IUploadManager {

	@Autowired
	private CasesManager casesService;
	@Autowired
	private CasesDetailManager caseDetailService;
	@Autowired
	private ResManager resService;

	@Autowired
	private IResDetailManager resDetailService;

	@Override
	public void AddCase(Integer userid, String casename, String sourcepath) {

		ExcelReader excel = new ExcelReader(sourcepath);
		List<String> line = excel.readLine(1);
		Date data = new Date();
		// 更新用例数据
		Cases cases = new Cases();
		cases.setCaseName(casename);
		cases.setType(line.get(1));
		cases.setUserId(userid);
		cases.setCreateTime(data);
		Integer caseid = casesService.Updater(cases);
		MyLogger.logger.info(caseid);
		for (int k = 0; k < excel.getSheets(); k++) {
			excel.useSheet(k);
			CaseDetail detail = new CaseDetail();
			detail.setCaseId(caseid);
			detail.setType(3);
			detail.setCaseName(excel.sheet.getSheetName());
			caseDetailService.Updater(detail);
			for (int i = 1; i < excel.rows; i++) {
				detail = new CaseDetail();
				line = excel.readLine(i);
				// 处理字符串长度
				for (int j = 2; j < MyLogger.size.length - 1 && j < line.size(); j++) {
					if (line.get(j).length() > MyLogger.size[j + 1]) {
						line.set(j, line.get(j).substring(0, MyLogger.size[j + 1]));
					}
				}
				detail.setCaseId(caseid);
				if (line.get(0).length() > 1) {
					detail.setType(0);
					detail.setCaseName(line.get(0));
				} else {
					if (line.get(1).length() > 1) {
						detail.setType(1);
						detail.setCaseName(line.get(1));
					} else {
						detail.setType(2);
						detail.setCaseName(line.get(2));
						detail.setKeyWord(line.get(3));
						detail.setParam1(line.get(4));
						detail.setParam2(line.get(5));
						detail.setParam3(line.get(6));
						detail.setStatus("N/A");
						detail.setActual("");
					}
				}
				caseDetailService.Updater(detail);
			}
		}
	}

	@Override
	public void AddRes(Integer userid, Integer caseid, String casename, String sourcepath) {

		ExcelReader excel = new ExcelReader(sourcepath);
		List<String> line = excel.readLine(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		Res res = new Res();
		res.setCaseId(caseid);
		res.setUserId(userid);
		res.setReportName(line.get(2));
		res.setResName(casename);
		res.setType(line.get(1));
		try {
			date = sdf.parse(line.get(3));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setStartTime(date);
		date = new Date();
		try {
			date = sdf.parse(line.get(4));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setEndTime(date);
		resService.Updater(res);

		int count = 0;
		int pass = 0;

		// 更新结果数据
		for (int k = 0; k < excel.getSheets(); k++) {
			excel.useSheet(k);
			ResDetail detail = new ResDetail();
			detail.setResId(res.getId());
			detail.setType(3);
			detail.setCaseName(excel.sheet.getSheetName());
			resDetailService.Updater(detail);
			for (int i = 1; i < excel.rows; i++) {
				detail = new ResDetail();
				line = excel.readLine(i);
				// 处理字符串长度
				for (int j = 2; j < MyLogger.size.length - 1 && j < line.size(); j++) {
					if (line.get(j).length() > MyLogger.size[j + 1]) {
						line.set(j, line.get(j).substring(0, MyLogger.size[j + 1]));
					}
				}
				detail.setResId(res.getId());
				if (line.get(0).length() > 1) {
					detail.setType(0);
					detail.setCaseName(line.get(0));
				} else {
					if (line.get(1).length() > 1) {
						detail.setType(1);
						detail.setCaseName(line.get(1));
					} else {
						count++;
						detail.setType(2);
						detail.setCaseName(line.get(2));
						detail.setKeyWord(line.get(3));
						detail.setParam1(line.get(4));
						detail.setParam2(line.get(5));
						detail.setParam3(line.get(6));
						detail.setStatus(line.get(7));
						if (line.get(7).equals("PASS")) {
							pass++;
						}
						detail.setActual(line.get(8));
						System.out.println(detail.getActual());
					}
				}
				resDetailService.Updater(detail);
			}
		}
		if (count == pass) {
			res.setStatus("PASS");
		} else {
			res.setStatus("FAIL");
		}
		res.setCount(count);
		res.setPass(pass);
		res.setFail(count - pass);
		resService.Updater(res);
	}

}
