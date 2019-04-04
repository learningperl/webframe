package com.testing.service;

public interface IUploadManager {

	void AddCase(Integer userid,String casename,String path);

	void AddRes(Integer userid, Integer caseid, String resname, String sourcepath);

}
