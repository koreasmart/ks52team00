package ksmybatis.manage.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ksmybatis.common.dto.CommonGroupCode;
import ksmybatis.common.mapper.CommonMapper;
import ksmybatis.manage.member.dto.Member;
import ksmybatis.manage.member.mapper.MemberMapper;
import ksmybatis.manage.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Controller HTTP 요청 응답
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

	private final MemberService memberService;
	private final MemberMapper memberMapper;
	private final CommonMapper commonMapper;
	
	/**
	 * RedirectAttributes : 리디렉션 주소 뒤에 쿼리파라미터를 추가해주는 객체
	 * @return
	 */
	@PostMapping("/removeMember")
	public String removeMember(@RequestParam(name="memberId") String memberId,
							   @RequestParam(name="memberPw") String memberPw,
							   RedirectAttributes reAttr) {
		
		 
		String viewName = "redirect:/member/removeMember";
		String msg = "회원의 정보가 일치하지 않습니다.";
		
		Map<String, Object> matchResultMap = memberService.matchedMember(memberId, memberPw);
		boolean checkMember = (boolean) matchResultMap.get("isMatch");
		
		if(checkMember) {
			Member memberInfo = (Member) matchResultMap.get("memberInfo");
			String memberGrade = memberInfo.getMemberGrade();
			memberService.removeMember(memberGrade, memberId);
			viewName = "redirect:/member/memberList";
		}else {			
			reAttr.addAttribute("memberId", memberId);
			reAttr.addAttribute("msg", msg);
		}
		return viewName;
	}
	
	
	@GetMapping("/removeMember")
	public String removeMember(@RequestParam(name="memberId") String memberId,
							   @RequestParam(name="msg", required = false) String msg,
							   Model model) {
		
		model.addAttribute("title", "회원탈퇴");
		model.addAttribute("memberId", memberId);
		if(msg != null) model.addAttribute("msg", msg);
		
		return "manage/member/removeMember";
	}
	
	
	@PostMapping("/modifyMember")
	public String modifyMember(Member member) {
		
		memberService.modifyMember(member);
		
		return "redirect:/member/memberList";
	}
	
	@GetMapping("/modifyMember")
	public String modifyMember(@RequestParam(name="memberId") String memberId,
							   Model model) {
		
		CommonGroupCode grade = commonMapper.getCommGroupCode("회원등급");
		Member memberInfo = memberMapper.getMemberInfoById(memberId);
		log.info("memberInfo {}", memberInfo);
		model.addAttribute("title", "회원수정");
		model.addAttribute("grade", grade);
		model.addAttribute("memberInfo", memberInfo);
		
		return "manage/member/modifyMember";
	}
	
	@PostMapping("/addMember")
	public String addMember(Member member) {
		
		log.info("member: {}", member);
		memberService.addMember(member);
		
		return "redirect:/member/memberList";
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam(value="memberId") String memberId) {
		
		log.info("memberId : {}", memberId);
		
		boolean isDuplicate = false;
		
		isDuplicate = memberMapper.memberCheckById(memberId);
		
		return isDuplicate;
	}
	
	
	
	@GetMapping("/addMember")
	public String addMember(Model model) {
	
		//List<MemberGrade> gradeList = memberService.getGradeList();
		//log.info("gradeList:{}", gradeList);
		CommonGroupCode grade = commonMapper.getCommGroupCode("회원등급");
		log.info("grade: {}", grade);
		
		model.addAttribute("title", "회원가입");
		model.addAttribute("grade", grade);
		
		return "manage/member/addMember";
	}
	

	@GetMapping("/memberList")
	public String getMemberList(Model model) {
		
		List<Member> memberList = memberService.getMemberList();
		
		model.addAttribute("memberList", memberList);
		
		log.info("memberList :{}", memberList);
		
		return "manage/member/memberList";
	}
}













