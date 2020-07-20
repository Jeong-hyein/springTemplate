package com.dbal.app.emp.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller //Bean등록, 디스패처 서블릿이 인식할 수 있는 컨트롤러로 변환 //@Component 상속
public class EmpController {
	


	@Autowired
	EmpService empService;
	
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
		//FileService.i
		nsert();
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

	
	//단건조회
	@RequestMapping("/getEmp/{employeeId}/{firstName}")  //getEmp?employeeId=aaaa, 여러개도 가능 /{firstName}
	public String getEmp(@PathVariable Integer employeeId, @PathVariable String firstName) {
		System.out.println(employeeId + " : " + firstName);
		return "home";
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
	
	
}
