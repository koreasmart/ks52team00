package ksmybatis.manage.vendors.dto;

import ksmybatis.manage.member.dto.Member;
import lombok.Data;

@Data
public class Vendor {

	private String vendorCode; 
	private String vendorMemberId; 
	private String vendorBrno; 
	private String vendorName; 
	private String vendorAddr; 
	private String vendorDaddr; 
	private int vendorZip; 
	private String vendorTelNo;
	
	private Member member;
}
