package ksmybatis.files.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ksmybatis.files.dto.FileDto;
import ksmybatis.files.mapper.FileMapper;
import ksmybatis.files.util.FilesUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService{
	
	private final FilesUtils filesUtils;
	private final FileMapper fileMapper;
	
	@Override
	public void removeFileByIdx(String fileIdx) {
		FileDto fileInfo = fileMapper.getFileInfoByIdx(fileIdx);
		String path = "/home/teamproject" + fileInfo.getFilePath();
		boolean isDelete = filesUtils.deleteFileByPath(path);
		if(isDelete) fileMapper.removeFileByIdx(fileIdx);
	}
	
	@Override
	public void addFile(MultipartFile multipartFile) {
		FileDto fileInfo = filesUtils.uploadFile(multipartFile);
		if(fileInfo != null) fileMapper.addFile(fileInfo);
	}
	
	@Override
	public void addFiles(MultipartFile[] multipartFile) {
		List<FileDto> fileList = filesUtils.uploadFiles(multipartFile);
		if(!fileList.isEmpty()) fileMapper.addFiles(fileList);
	}
}






