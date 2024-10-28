package ksmybatis.common.dto;

import lombok.Data;

@Data
public class CommonCode {

	private String groupCode;
	private String commCode;
	private String commName;
	private String commEngName;
	private String commCodeUseYN;
	private String commCodeRegDate;
	
	private CommonGroupCode commonGroupCode;
	
}
