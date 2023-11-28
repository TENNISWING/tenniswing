package com.tenniswing.project.attach.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.Value;

@Controller
public class AttachController {
//	@Value("${file.upload.path}")
//	private String uploadPath;
//	
//	
//	@GetMapping("upload")
//	public void getUploadPath() {}
//	
//	//임포트할때 nio로 해야함
//	@PostMapping("/uploadsAjax")
//	@ResponseBody
//	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) { // MultipartFile[] 배열로 파일데이터를 내장하고있음
//	    
//		List<String> imageList = new ArrayList<>();
//		
//	    for(MultipartFile uploadFile : uploadFiles){ //배열이니 반복문 돌림 
//	    	if(uploadFile.getContentType().startsWith("image") == false){ //이미지에 대해서만 처리하도록 제한을 걺
//	    		System.err.println("this file is not image type");
//	    		return null;
//	        }
//	  
//	        String originalName = uploadFile.getOriginalFilename(); //업로드를 할거니 사용자가 업로드할때 사용할 이름과 내부에 저장된 경로가 필요함
//	        String fileName = originalName.substring(originalName.lastIndexOf("//")+1); //사용자가 저장한 파일명에 변화를 일으킴 중복파일 올리는 경우가 많아서
//	        
//	        System.out.println("fileName : " + fileName);
//	    
//	        //날짜 폴더 생성 - 파일관리하기 편하게 날짜 자동생성
//	        String folderPath = makeFolder();
//	        //UUID - 랜덤값, 동일한 이름이라 하더라도 유니크할수있도록 랜덤값 생성
//	        String uuid = UUID.randomUUID().toString();
//	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
//	        
//	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName; //업로드하는 파일의 실제이름 생성
//	        
//	        String saveName = uploadPath + File.separator + uploadFileName; //실제 저장할 경로를 문자로 저장
//	        
//	        Path savePath = Paths.get(saveName); //Paths => 파일경로에 대해 처리하는 클래스
//	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
//	        System.out.println("path : " + saveName);
//	        try{
//	        	uploadFile.transferTo(savePath); //이미지를 지정한 경로로 보내는 역할을 함 
//	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
//	        } catch (IOException e) {
//	             e.printStackTrace();	             
//	        }
//	        // DB에 해당 경로 저장 
//	        // 1) 사용자가 업로드할 때 사용한 파일명
//	        // 2) 실제 서버에 업로드할 때 사용한 경로
//	        imageList.add(setImagePath(uploadFileName));
//	     }
//	    
//	    return imageList;
//	}
//	private String makeFolder() {
//		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//		// LocalDate를 문자열로 포멧
//		String folderPath = str.replace("/", File.separator); 
//		File uploadPathFoler = new File(uploadPath, folderPath);
//		// File newFile= new File(dir,"파일명");
//		if (uploadPathFoler.exists() == false) {
//			uploadPathFoler.mkdirs();
//			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
//			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
//			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수 보통 s붙은거 많이씀 상위디렉토리 생성해줘서
//		}
//		
//		return folderPath;
//	}
//	
//	private String setImagePath(String uploadFileName) { // 화면에서 사용하는 경로 반환해줌
//		return uploadFileName.replace(File.separator, "/");
//	}

}
