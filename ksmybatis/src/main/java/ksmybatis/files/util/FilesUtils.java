package ksmybatis.files.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import ksmybatis.files.dto.FileDto;

@Component
public class FilesUtils {

	@Value("${file.path}")
	private String fileRootPath;
	
	// 파일 삭제
	public boolean deleteFileByPath(String path) {
		boolean isDelete = false;
		
		File file = new File(path);
		
		Path targetPath = Paths.get(file.getAbsolutePath());
		
		try {
			isDelete = Files.deleteIfExists(targetPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return isDelete;
	}
	
	// 단일 파일 업로드
	public FileDto uploadFile(MultipartFile multipartFile) {
		
		FileDto fileInfo = storeFile(multipartFile);
		
		return fileInfo;
	}
	
	// 다중 파일 업로드
	public List<FileDto> uploadFiles(MultipartFile[] multipartFiles){
		List<FileDto> fileList = new ArrayList<FileDto>();
		FileDto fileInfo = null;
		for(MultipartFile multipartFile : multipartFiles) {
			fileInfo = storeFile(multipartFile);
			if(fileInfo != null) fileList.add(fileInfo);
		}
		return fileList;
	}
	
	
	// 파일업로드
	private FileDto storeFile(MultipartFile multipartFile) {
		
		if(multipartFile.isEmpty()) return null;
		// 현재날짜(ex:20241025)
		LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
		// 날짜의 포맷형식 지정
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		// 콘텐츠 파일 타입(디렉토리)
		String contentType = multipartFile.getContentType();
		
		if(contentType != null && contentType.indexOf("image") > -1) {
			contentType = "/image/";
		}else {
			contentType = "/file/";
		}
		// /home/teamproject/attachment/20241025/image/
		String path = Paths.get(fileRootPath + "attachment/"+ now.format(formatter) + contentType).toString();
		
		// 디렉토리 생성
		createDirectory(path);

		String newFileName = "";
		
		// 파일명.파일타입
		String[] fileNameSplit = multipartFile.getOriginalFilename().split("\\.");
		newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileNameSplit[1];
		
		
		byte[] bytes;
		Path uploadPath = Paths.get(path + "/" + newFileName);
		
		FileDto fileInfo = null;
		
		try {
			bytes = multipartFile.getBytes();
			
			Files.write(uploadPath, bytes);
			
			DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyMMdd");
			
			String fileIdx = "file_" + now.format(fileFormatter) + Long.toString(System.nanoTime());
			
			// /attachment/20241025/image/asdlfjadfj.jpg
			String filePath = "/attachment/"+ now.format(formatter) + contentType + newFileName;
			
			fileInfo = FileDto.builder()
							  .fileIdx(fileIdx)
							  .fileOriginalName(multipartFile.getOriginalFilename())
							  .fileNewName(newFileName)
							  .filePath(filePath)
							  .fileSize(multipartFile.getSize())
							  .build();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return fileInfo;
	}
	
	private void createDirectory(String path) {
		// /home/teamproject/attachment/image/
		File isFile = new File(path);
		if(!isFile.isDirectory()) {
			isFile.mkdirs();
		}
		if(!isFile.canWrite()) {
			isFile.setWritable(true);
		}
		if(!isFile.canRead()) {
			isFile.setReadable(true);
		}
	}
}












