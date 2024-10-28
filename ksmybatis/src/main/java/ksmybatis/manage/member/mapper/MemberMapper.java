package ksmybatis.manage.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.manage.member.dto.Member;
import ksmybatis.manage.member.dto.MemberGrade;

/**
 * @Mapper DAO(data access object)
 */
@Mapper
public interface MemberMapper {
	
	// 특정회원 탈퇴
	int removeMemberById(String memberId);
	
	// 특정회원의 로그인 이력삭제
	int removeMemberLoginLogById(String memberId);
	
	// 특정회원조회
	Member getMemberInfoById(String memberId);
	
	// 회원수정
	int modifyMember(Member member);
	
	// 회원가입
	int addMember(Member member);
	
	// 아이디 중복체크
	boolean memberCheckById(String memberId);
	
	// 회원목록조회
	List<Member> getMemberList();
	
	// 회원등급조회
	List<MemberGrade> getGradeList();

}
