package ksmybatis.login.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ksmybatis.login.service.ILoginService;
import ksmybatis.manage.member.dto.Member;
import ksmybatis.manage.member.service.MemberService;
import ksmybatis.page.PageInfo;
import ksmybatis.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
	
	private final MemberService memberService;
	private final ILoginService loginService;
	
	@GetMapping("/loginHistory")
	public String loginHistoryList(Pageable pageable, Model model) {
		
		log.info("pageable: {}", pageable);
		
		PageInfo<Map<String, Object>> pageInfo = loginService.getLoginHistoryList(pageable);
		
		model.addAttribute("title", "로그인 이력");
		model.addAttribute("currentPage", 	pageInfo.getCurrentPage());
		model.addAttribute("lastPage", 		pageInfo.getLastPage());
		model.addAttribute("contents", 		pageInfo.getContents());
		model.addAttribute("startPageNum", 	pageInfo.getStartPageNum());
		model.addAttribute("endPageNum", 	pageInfo.getEndPageNum());
		 
		return "login/loginHistoryList";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@PostMapping("/loginProc")
	public String loginProcess(String memberId, String memberPw,
							   HttpSession session, RedirectAttributes reAttr) {
		
		log.info("id: {}, pw: {}", memberId, memberPw);
		
		String viewName = "redirect:/login";
		String msg = "회원의 정보가 일치하지 않습니다.";
		
		Map<String, Object> loginMap = memberService.matchedMember(memberId, memberPw);
		boolean checkMember = (boolean) loginMap.get("isMatch");
		
		if(checkMember) {
			Member memberInfo = (Member) loginMap.get("memberInfo");
			String memberGrade = memberInfo.getMemberGrade();
			String memberName = memberInfo.getMemberName();
			
			session.setAttribute("SID", memberId);
			session.setAttribute("SGRD", memberGrade);
			session.setAttribute("SNAME", memberName);
			
			viewName = "redirect:/";
		}else {
			reAttr.addAttribute("msg", msg);
		}
		return viewName;
	}

	@GetMapping("/login")
	public String login(@RequestParam(name="msg", required = false) String msg,
						Model model) {
		
		model.addAttribute("title", "로그인화면");
		if(msg != null) model.addAttribute("msg", msg);
		
		return "login/login";
	}
}











