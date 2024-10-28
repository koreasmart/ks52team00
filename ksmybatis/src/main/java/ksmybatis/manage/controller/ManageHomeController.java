package ksmybatis.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageHomeController {

	@GetMapping("/")
	public String adminMain() {
		return "manage/index";
	}
}
