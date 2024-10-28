package ksmybatis.manage.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.manage.member.dto.Member;
import ksmybatis.manage.member.dto.MemberGrade;
import ksmybatis.manage.member.mapper.MemberMapper;
import ksmybatis.manage.order.mapper.OrderMapper;
import ksmybatis.manage.products.mapper.ProductsMapper;
import ksmybatis.manage.vendors.mapper.VendorsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Service 비즈니스로직
 * @AllArgsConstructor 클래스 내부에 선언한 프로퍼티 모두 인수로 넣고 생성자 메소드 선언
 * @NoArgsConstructor 인수없이 생성자 메소드 선언
 * @RequiredArgsConstructor final 로 선언한 프로퍼티를 인수로 넣고 생성자 메소드 선언
 * @Transactional A(원자성)C(일관성)I(고립성)D(영속성, 지속성)
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper memberMapper;
	private final OrderMapper orderMapper;
	private final ProductsMapper productsMapper;
	private final VendorsMapper vendorsMapper;
	
	@Override
	public void removeMember(String memberGrade, String memberId) {
		
		switch (memberGrade) {
			case "mbr_grd_2" -> {
				List<Integer> orderNumList = orderMapper.getOrderNumBySellerId(memberId);
				log.info("orderNumList: {}", orderNumList);
				if(orderNumList != null && orderNumList.size() > 0) {
					orderMapper.removeOrderItemsByOrderNumList(orderNumList);
					orderMapper.removeOrderByOrderNumList(orderNumList);
				}
				productsMapper.removeProductsById(memberId);
				vendorsMapper.removeVendorsById(memberId);
			}
			case "mbr_grd_3" -> {
				orderMapper.removeOrderItemsById(memberId);
				orderMapper.removeOrderById(memberId);
			}
		}
		
		memberMapper.removeMemberLoginLogById(memberId);
		memberMapper.removeMemberById(memberId);
		
	}
	
	@Override
	public Map<String, Object> matchedMember(String memberId, String memberPw) {
		boolean isMatch = false;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Member memberInfo = memberMapper.getMemberInfoById(memberId);
		
		if(memberInfo != null) {
			String checkPw = memberInfo.getMemberPw();
			if(checkPw.equals(memberPw)) {
				isMatch = true;
				resultMap.put("memberInfo", memberInfo);
			}
		}
		
		resultMap.put("isMatch", isMatch);
		
		return resultMap;
	}
	
	@Override
	public void modifyMember(Member member) {
		
		memberMapper.modifyMember(member);
		
	}
	
	@Override
	public void addMember(Member member) {
		int result = memberMapper.addMember(member);	
	}

	@Override
	public List<MemberGrade> getGradeList() {
		
		return memberMapper.getGradeList();
	}
	
	@Override
	public List<Member> getMemberList() {
		
		List<Member> memberList = memberMapper.getMemberList();
		
		return memberList;
	}
}






















