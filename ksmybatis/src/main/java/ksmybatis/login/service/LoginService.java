package ksmybatis.login.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.login.mapper.LoginMapper;
import ksmybatis.page.PageInfo;
import ksmybatis.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoginService implements ILoginService{
	
	private final LoginMapper loginMapper;
	
	@Override
	public PageInfo<Map<String, Object>> getLoginHistoryList(Pageable pageable) {
		
		int rowCnt = loginMapper.getLoginHistoryCount();
		
		List<Map<String, Object>> contents = loginMapper.getLoginHistoryList(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
}












