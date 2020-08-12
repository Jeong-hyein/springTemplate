package com.dbal.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("userexcel.xls") //userexcel.xlsx 확장자 안맞아서 xlsx로 해줘야함
	public ModelAndView  userexcel(UserVO vo) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("commonExcelView");
		mv.addObject("datas", userService.getUserListMap(vo));
		mv.addObject("filename", "userlist");
		mv.addObject("headers", new String[] {"id", "name"}); //두개만 보고싶으면 두개만 볼수있다
		return mv;		
	}

}
