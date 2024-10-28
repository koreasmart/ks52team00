package ksmybatis.files.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDto {
	
	private String fileIdx; 
	private String fileOriginalName; 
	private String fileNewName; 
	private String filePath; 
	private Long fileSize; 
	private String fileRegDate;

}
