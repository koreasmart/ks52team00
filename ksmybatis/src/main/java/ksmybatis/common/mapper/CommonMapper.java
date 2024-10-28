package ksmybatis.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.common.dto.CommonGroupCode;

@Mapper
public interface CommonMapper {
	
	// 자동증가코드 생성
	String getPrimaryKey(String tableName, String columnName, String codePattern);

	CommonGroupCode getCommGroupCode(String groupName);
}
