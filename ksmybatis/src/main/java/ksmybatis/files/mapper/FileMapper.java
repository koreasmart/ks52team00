package ksmybatis.files.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.files.dto.FileDto;

@Mapper
public interface FileMapper {
	
	List<FileDto> getFileList();

	int addFile(FileDto fileInfo);
	
	int addFiles(List<FileDto> fileList);

	FileDto getFileInfoByIdx(String fileIdx);
	
	int removeFileByIdx(String fileIdx);
}









