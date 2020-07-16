package com.dbal.app.emp;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dbal.app.emp.mapper.EmpDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/config/*-context.xml")

public class EmpDAOClient {
	
	@Autowired EmpDAO dao;
	
//	@Test
//	public void empListTest() {
//		EmpVO empVO = new EmpVO();
//		//empVO.setDepartmentId("90");
//		//empVO.setEmployeeId("100");
//		//empVO.setSalary(3000);
//		//empVO.setFirstName("Steven");
//		empVO.setEmployeeIds(new Integer[] {100,101,102});
//		List<EmpVO> list = dao.getEmpList(empVO);
//		System.out.println(list);
//	}
	
	
	
	//@Test
	public void getEmp() {
		EmpVO paraVO = new EmpVO();
		//paraVO.setEmployee_id("114");
		EmpVO resultVO = dao.getEmp(paraVO);
		System.out.println(resultVO);
	}
	
	
//	@Test @
//	public void insertEmp() {
//		EmpVO paraVO = new EmpVO();
//		paraVO.setEmployeeId("211");
//		paraVO.setFirstName("jung");
//		paraVO.setLastName("hyein");
//		paraVO.setEmail("aa@aa");
//		paraVO.setHireDate("2020/10/30");
//		paraVO.setJobId("IT_PROG");
//		dao.insertEmp(paraVO);
//	}
	
//builder는 선택임
//	public void insertEmp() {
//		EmpVO paraVO = EmpVO.builder()
//				   			.employeeId("150")
//				   			.firstName("aa")
//				   			.lastName("bbb")
//				   			.email("a@b")
//				   			.build();
//		dao.insertEmp(paraVO);
//				
//	}
	
//	@Test
//	public void updateEmp() {
//		EmpVO paraVO = new EmpVO();
//		paraVO.setEmployeeId("211");
//		paraVO.setFirstName("j");
//		dao.updateEmp(paraVO);
//	}
	
//	@Test
//	public void deleteEmp() {
//		EmpVO paraVO = new EmpVO();
//		paraVO.setEmployeeId("211");
//		dao.deleteEmp(paraVO);
//	}
	
	
}
