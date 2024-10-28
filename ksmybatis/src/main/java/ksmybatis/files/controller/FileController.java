package ksmybatis.files.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ksmybatis.files.dto.FileDto;
import ksmybatis.files.mapper.FileMapper;
import ksmybatis.files.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FileController {
	
	private final FileService fileService;
	private final FileMapper fileMapper;

	@GetMapping("/removeFile")
	public String removeFile(@RequestParam(name="fileIdx") String fileIdx) {
		
		fileService.removeFileByIdx(fileIdx);
		
		return "redirect:/filedownloadView";
	}
	
	
	@RequestMapping(value="/download")
	@ResponseBody
	public ResponseEntity<Object> archiveDownload(@RequestParam(value="fileIdx", required = false) String fileIdx,
							HttpServletRequest request,HttpServletResponse response) throws URISyntaxException{
		
		
		if(fileIdx != null) {
			FileDto fileDto = fileMapper.getFileInfoByIdx(fileIdx);
			
			File file = new File("/home/teamproject" + fileDto.getFilePath());
			
			Path path = Paths.get(file.getAbsolutePath());
	        Resource resource;
			try {
				resource = new UrlResource(path.toUri());
				String contentType = null;
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
				if(contentType == null) {
					contentType = "application/octet-stream";
				}
				return ResponseEntity.ok()
						.contentType(MediaType.parseMediaType(contentType))
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fileDto.getFileOriginalName(),"UTF-8").replaceAll("\\+", "%20") + "\";")
						.body(resource);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		URI redirectUri = new URI("/");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(redirectUri);
		
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	@GetMapping("/filedownloadView")
	public String filedownloadView(Model model) {
		
		model.addAttribute("title", "파일목록");
		model.addAttribute("fileList", fileMapper.getFileList());
		
		return "manage/file/filedownloadView";
	}
	
	
	@PostMapping("/fileupload")
	public String fileUploadPro(@RequestPart(name="files", required = false) MultipartFile[] multipartFile) {
		
		fileService.addFiles(multipartFile);
		
		return "redirect:/fileupload";
	}

	@GetMapping("/fileupload")
	public String fileUploadView(Model model) {
		
		model.addAttribute("title", "파일업로드");
		
		return "manage/file/fileupload";
	}
}
