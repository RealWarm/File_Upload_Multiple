package com.acorn.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;


@Log4j
@Controller
public class UploadController {
	
	// 1. 파일업로드 화면을 보여주는 URI : /uploadForm, Method: GET
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("UploadController::uploadForm() invoked.");
		
		;;
	} // uploadForm
	
	
	// 2. 파일업로드 요청을 처리하는 URI : /uploadFormAction, Method: POST
	@PostMapping("/uploadFormAction")
	public void uploadFormAction(
			Model model, 
			String name,
			@RequestParam("uploadFile") 
				MultipartFile[] uploadFiles) 
		throws IOException {
		
		log.info("UploadController::uploadFormAction() invoked.");
		
		log.info("\t+ name: "+ name);
		log.info("\t+ uploadFiles: "+
				Arrays.toString(uploadFiles));
		
		List<String> successFiles = new ArrayList<>();
		
		for(MultipartFile file : uploadFiles) {
			log.info("\t\t =================================");
			log.info("\t\t* 1. contentType: "		+file.getContentType());
			log.info("\t\t* 2. name: "				+file.getName());
			log.info("\t\t* 3. originalFilename: "	+file.getOriginalFilename());
			log.info("\t\t* 4. size: "				+file.getSize());
			log.info("\t\t* 5. clazz: "				+file.getClass());
			log.info("\t\t* 6. resource: "			+file.getResource());
			log.info("\t\t* 7. byte[]: "			+file.getBytes().length);
			log.info("\t\t* 8. isEmpty: "			+file.isEmpty());
			
			// 파일을 정해진 위치에 저장하기
			String uploadTempPath = "C:\\App";
			String uploadTaregtPath = "C:\\App\\eclipse-jee-2019-03-R-win32-x86_64";
			
			File f = new File(uploadTempPath, file.getOriginalFilename());
			f.deleteOnExit();  // **** 중요2 ****
			
			// 지정된 임시경로에 수신한 파일저장
			file.transferTo(f);
			
			// (**매우 중요 **)임시경로에 저장된 파일을 
			// 최종 타겟 폴더에 저장
			f.renameTo(
				new File(
						uploadTaregtPath, 
						file.getOriginalFilename()
					)
			); // 최종 타겟 폴더로 Move
			
			successFiles.add(file.getOriginalFilename());
			
		} // enhanced for
		
		model.addAttribute("successFiles", successFiles);
		
		log.info("Done.");
		
	} // uploadFormAction

} // end class





