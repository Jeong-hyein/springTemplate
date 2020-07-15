package com.dbal.app.emp.mapper;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dbal.app.emp.EmpVO;

@Repository  //Bean등록, connection 설정  //@Component 상속
public class EmpDAO {
	
	@Autowired 
	private SqlSessionTemplate mybatis; // SqlSessionTemplate : mybatis에서 지원
	
	//단건조회
	public EmpVO getEmp(EmpVO empVO) {
		return mybatis.selectOne("com.dbal.app.emp.mapper.EmpDAO.getEmp", empVO); //namespace.id값
	}
	
	//전체조회
	public List<EmpVO> getEmpList(EmpVO empVO){
		return mybatis.selectList("com.dbal.app.emp.mapper.EmpDAO.getEmpList", empVO); 
	}
	
	//등록
	public void insertEmp(EmpVO empVO) {
		mybatis.insert("com.dbal.app.emp.mapper.EmpDAO.insertEmp", empVO);
	}
	
	//수정
	public void updateEmp(EmpVO empVO) {
		mybatis.update("com.dbal.app.emp.mapper.EmpDAO.updateEmp", empVO);
	}

	//삭제
	public void deleteEmp(EmpVO empVO) {
		mybatis.delete("com.dbal.app.emp.mapper.EmpDAO.deleteEmp", empVO);
	}
	
	
	public String getName(Integer empid) {
		return mybatis.selectOne("com.dbal.app.emp.mapper.EmpDAO.getName", empid);
	}
	
	public int getCnt(){
		return mybatis.selectOne("com.dbal.app.emp.mapper.EmpDAO.getCnt");
	}
	
//	public HashMap<String, Object> getEmpMap() {
//		return mybatis.selectMap("com.dbal.app.emp.mapper.EmpDAO.getEmpMap", mapKey);
//	}


	
}
