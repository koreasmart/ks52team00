package ksmybatis.files.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	// 파일 삭제
	void removeFileByIdx(String fileIdx);
	
	// 단일 파일 등록
	void addFile(MultipartFile multipartFile);
	
	// 다중 파일 등록
	void addFiles(MultipartFile[] multipartFile);
}
