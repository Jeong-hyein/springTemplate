package com.dbal.app.users;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController  //@responseBody가 있는거랑 같다 
public class RestfulUserController {
	@Autowired
	UserService userService;
	
//	url은 다 똑같고 타입으로 구분
	
	//전체조회
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<UserVO> getUserList(Model model, UserVO vo) {
		return  userService.getUserList(vo);  //view페이지 아니고 자바객체를 리턴
	}
	
	//단건조회
	@RequestMapping(value="/users/{id}",  method=RequestMethod.GET)
	public UserVO getUser(@PathVariable String id, UserVO vo, Model model) {
		vo.setId(id);
		return  userService.getUser(vo);
	}
	
	//삭제
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public Map  getUserList( @PathVariable String id, UserVO vo, Model model) {
		vo.setId(id);
		userService.deleteUser(vo);
		Map result = new HashMap<String, Object>();
		result.put("result", Boolean.TRUE);
		return result;
	}
	//등록
	
	@RequestMapping(value="/users"
			,method=RequestMethod.POST
            ,headers = {"Content-type=application/json" }
	)
	
//	jsp페이지에서 data를 $(#"form1").serialize()로 바꾸면 @RequestBody UserVO vo에서 @RequestBody 노필요
	public Map insertUser( UserVO vo, Model model) {
		userService.insertUser(vo);
		return  Collections.singletonMap("result", true);
	}
	
	//수정
	@RequestMapping(value="/users"
			,method=RequestMethod.PUT
	 		,consumes="application/json"      //요청헤더
	)
	public UserVO updateUser(@RequestBody UserVO vo, Model model) {
		userService.updateUser(vo);
		return  vo;
	}	
	
//	API를 가져와서 내가 정해준 VO에 담아줌
	@RequestMapping(value="/respAPI")
	public Map respAPI() {					//URL
		RestTemplate rest = new RestTemplate();
		return rest.getForObject("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20200713", Map.class);
	}
	
}
