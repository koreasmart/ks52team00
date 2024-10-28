package ksmybatis.common.dto;

import java.util.List;

import lombok.Data;

@Data
public class CommonGroupCode {

	private String groupCode;
	private String groupName;
	private String groupEngName;
	private String groupCodeRegDate;
	
	private List<CommonCode> commCodeList;
}
