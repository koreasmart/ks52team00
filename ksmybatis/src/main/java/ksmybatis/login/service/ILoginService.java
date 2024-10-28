package ksmybatis.login.service;

import java.util.Map;

import ksmybatis.page.PageInfo;
import ksmybatis.page.Pageable;

public interface ILoginService {
	
	// 로그인 이력 조회
	PageInfo<Map<String, Object>> getLoginHistoryList(Pageable pageable);
}
