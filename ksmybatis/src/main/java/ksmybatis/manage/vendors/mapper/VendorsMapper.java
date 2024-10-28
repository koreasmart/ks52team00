package ksmybatis.manage.vendors.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.manage.vendors.dto.Vendor;

@Mapper
public interface VendorsMapper {
	
	// 판매처 정보 조회(some: 판매처코드, 판매처명 | all: 전체)
	List<Vendor> getVendorInfoByParam(String param);

	// 판매자가 등록한 판매처 삭제
	int removeVendorsById(String sellerId);
}
