package com.dbal.app.emp.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dbal.app.common.FileRenamePolicy;
import com.dbal.app.emp.EmpVO;
import com.dbal.app.emp.service.EmpService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller //Bean등록, 디스패처 서블릿이 인식할 수 있는 컨트롤러로 변환 //@Component 상속
public class EmpController {
	


	@Autowired
	EmpService empService;
	
	@Autowired
	//구현필드?가 두개일때 선택한다
	//dataSource랑 dataSourceSpied 중 하나 선택: config/datasource-context.xml
	@Qualifier("dataSourceSpied") 
	DataSource datasource;
	
	//등록폼
	@RequestMapping("insertFormEmp")
	public String insertFormEmp(EmpVO vo) {
		//세개다 레이아웃이 다름
		//return "empty/emp/insertEmp";
		//return "admin/emp/insertEmp";
		return "emp/insertEmp";
	}
	
	//등록처리
//	@RequestMapping("insertEmp")
//	public String insertEmp(@ModelAttribute("evo") EmpVO vo, Model model) {
//		System.out.println(vo.getFirstName() +" : " + vo.getLastName());
//		//model.addAttribute("empVO", vo); 안해도 넘어간다, evo랑 empVO랑 같음: view페이지에서 evo사용할수있음
//		//서비스 호출
//		return "home";
//	}
	
	//등록처리
	@RequestMapping("insertEmp")
	public String insertEmp(@ModelAttribute("evo") EmpVO vo, //커맨드 객체 자동으로 모델에 저장됨
							Model model, 
							@RequestParam String firstName,  //2. String firstName = request.getParameter("firstName")과같음
							@RequestParam(required = false, defaultValue = "kim", value = "lastName") String ln,
							//required=false: 필수아님, 변수명이랑 파라미터이름을 다르게 할때: value 사용
							@RequestParam Map map //3. 모든 파라미터 맵으로 받기
			
			
							) throws IOException {
		//첨부파일 처리
		MultipartFile[] files = vo.getUploadFile();
		for( MultipartFile file : files) {
		String filename = "";
		if (file != null && file.getSize() > 0) {
			File upFile = FileRenamePolicy.rename(new File("d:/upload",filename));
			filename = upFile.getName();
			file.transferTo(upFile);
			} 
		vo.setProfile(filename);  //db에는 파일이름이 
		//FileService.insert();
		}
		//서비스 호출
		empService.insertEmp(vo);
		System.out.println(vo.getMsg());
//		System.out.println(vo.getFirstName() +" : " + vo.getLastName());
//		System.out.println("parameter: " + firstName + " : " + ln);
//		System.out.println("map " + map.get("firstName") + " : " +map.get("lastName"));
		//model.addAttribute("empVO", vo); 안해도 넘어간다, evo랑 empVO랑 같음: view페이지에서 evo사용할수있음
		return "home";
	}

	
//	//단건조회
//	@RequestMapping("/getEmp/{employeeId}/{firstName}")  //getEmp?employeeId=aaaa, 여러개도 가능 /{firstName}
//	public String getEmp(@PathVariable Integer employeeId, @PathVariable String firstName) {
//		System.out.println(employeeId + " : " + firstName);
//		return "home";
//	}
	
	//단건조회
	@RequestMapping("/getEmp/{employeeId}")  //getEmp?employeeId=aaaa, 여러개도 가능 /{firstName}
	public String getEmp(@PathVariable String employeeId, Model model, EmpVO empVO) {
		empVO.setEmployeeId(employeeId);
		model.addAttribute("emp", empService.getEmp(empVO));
		return "empty/emp/getEmp"; //tiles가 필요없어서 empty붙임
	}
	
	
	//목록조회
	@RequestMapping("empList")
	public String empList(Model model, EmpVO empVO) {
		model.addAttribute("empList", empService.getEmpList(empVO));
		return "emp/empList";
	}
	
	//emp관리, ajax를 씀
	@RequestMapping("empClient")
	public String empClient() {
		return "admin/emp/empClient";
	}
	
	//ajax :목록, @ResponseBody: 결과가 json구조로
	@RequestMapping("ajaxEmpList")
	public @ResponseBody List<EmpVO> ajaxEmpList(EmpVO empVO) {
		return empService.getEmpList(empVO);
	}
	
	
	//차트 데이터
	@RequestMapping("getChartData.do")
	public @ResponseBody List<Map<String, Object>> getDeptEmpCnt() {
		return empService.getDeptEmpCnt();
	}
	
	
	//다운로드
	@RequestMapping("download")
	public ModelAndView download(@RequestParam String name) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("download");
		mv.addObject("downloadFile", new File("d:/upload", name));
		return mv;
	}
	
	//레포트 출력
	@RequestMapping("report.do")
	public void report(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Connection conn = datasource.getConnection();
		//컴파일된 jasper파일
		//InputStream jasperStream = getClass().getResourceAsStream("/reports/emplist.jasper"); //classPath부터 시작
		//JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		//JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
		//JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream()); //jasperPrint를 getOutputStream로 보내면 실행된다
		
		//소스를 받아서 컴파일 할수도 있다. emplist.jrxml을 받아서 emplist.jasper로 만든다 
		InputStream stream = getClass().getResourceAsStream("/reports/emplist.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(stream);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("p_departmentId",request.getParameter("dept")); //첫번째자리: 파라미터 이름이랑 동일해야함
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn); 
		//fillReport를 만들때 첫자리:jasperReport, 세번째: conn임, datasource가 들어와도됨, VO
		//두번째 자리: 파라미터 넘어가는 자리
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream()); 
		
	}
	
	@RequestMapping("report3.do")
	public ModelAndView report3(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pdfView");
		mv.addObject("filename", "/reports/emplist.jasper");
		return mv;
	}
	
	//
	@RequestMapping("getEmpAjax")
	@ResponseBody //자바구조를 json구조로 바꿔줌
	public EmpVO getEmpAjax(EmpVO empVO) {
		return empService.getEmp(empVO);
		
	}
	
	
	
}
