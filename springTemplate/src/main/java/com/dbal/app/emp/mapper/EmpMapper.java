package com.dbal.app.emp.mapper;

import java.util.List;
import java.util.Map;

import com.dbal.app.emp.EmpVO;

public interface EmpMapper {
	
	public EmpVO getEmp(EmpVO empVO);
	public List<EmpVO> getEmpList(EmpVO empVO);
	public void insertEmp(EmpVO empVO);
	public void updateEmp(EmpVO empVO);
	public void deleteEmp(EmpVO empVO);
	public String getName(Integer empid);
	public List<Map<String, Object>> getEmpMap();
	public List<Map<String, Object>> getDeptEmpCnt();
	
	//procedure
	public void insertEmpProc(EmpVO empVO);
	
}