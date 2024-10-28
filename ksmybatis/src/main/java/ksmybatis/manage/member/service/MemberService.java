package ksmybatis.manage.member.service;

import java.util.List;
import java.util.Map;

import ksmybatis.manage.member.dto.Member;
import ksmybatis.manage.member.dto.MemberGrade;

public interface MemberService {
	// 회원여부 확인
	Map<String, Object> matchedMember(String memberId, String memberPw);
	
	// 회원탈퇴
	void removeMember(String memberGrade, String memberId);
	
	// 회원수정
	void modifyMember(Member member);
	
	// 회원가입
	void addMember(Member member);
	
	//회원등급조회
	List<MemberGrade> getGradeList();

	//회원목록조회
	List<Member> getMemberList();

	

	

}
