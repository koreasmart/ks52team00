package ksmybatis.manage.member.dto;

import lombok.Data;

/**
 * @Data = @Setter + @Getter + @ToString
 */
@Data
public class Member {

	private String memberId; 
	private String memberPw; 
	private String memberName; 
	private String memberGrade; 
	private String memberAddr; 
	private String memberDaddr; 
	private int memberZip; 
	private String memberTelNo; 
	private String memberEmail; 
	private String memberRegDate;
	
}
