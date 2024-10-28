package ksmybatis.login.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.page.Pageable;

@Mapper
public interface LoginMapper {

	// 로그인 전체 이력 갯수 조회
	int getLoginHistoryCount();
	
	// 로그인 이력 조회
	List<Map<String, Object>> getLoginHistoryList(Pageable pageable);
	
}
