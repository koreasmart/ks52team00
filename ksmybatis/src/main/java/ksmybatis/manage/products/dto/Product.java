package ksmybatis.manage.products.dto;

import ksmybatis.manage.vendors.dto.Vendor;
import lombok.Data;

@Data
public class Product {

	private String productCode; 
	private String vendorCode; 
	private String productName; 
	private int productUntprc; 
	private String productRegDate;
	
	private Vendor vendor;
}
