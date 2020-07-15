package com.dbal.app.emp.service;

import java.util.List;
import java.util.Map;

import com.dbal.app.emp.EmpVO;

public interface EmpService {
	
	public EmpVO getEmp(EmpVO empVO);
	public List<EmpVO> getEmpList(EmpVO empVO);
	public void insertEmp(EmpVO empVO);
	public void updateEmp(EmpVO empVO);
	public void deleteEmp(EmpVO empVO);
	public List<Map<String, Object>> getDeptEmpCnt(); //통계정보
}
