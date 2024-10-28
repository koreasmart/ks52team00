package ksmybatis.manage.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
	
	// 주문상세 리스트 삭제
	int removeOrderById(String orderId);

	// 주문상세 리스트 삭제
	int removeOrderItemsById(String orderId);
	
	// 주문번호 주문 리스트 삭제
	int removeOrderByOrderNumList(List<Integer> orderNumList);

	// 주문번호 주문상세 리스트 삭제
	int removeOrderItemsByOrderNumList(List<Integer> orderNumList);
	
	// 판매자 등록한 상품을 구매한 주문번호 조회
	List<Integer> getOrderNumBySellerId(String sellerId); 
	
}
