package com.tenniswing.project.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tenniswing.project.attach.service.AttachVO;

@Component
public class FileUtils {
	@Value("${uploadPath}")
    private String uploadPath; 
	
	@Value("${upload}")
    private String upload; 

    /**
     * 다중 파일 업로드
     * @param multipartFiles - 파일 객체 List
     * @return DB에 저장할 파일 정보 List
     */
    public List<AttachVO> uploadFiles(final List<MultipartFile> multipartFiles) {
        List<AttachVO> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            files.add(uploadFile(multipartFile));
        }
        return files;
    }

    /**
     * 단일 파일 업로드
     * @param multipartFile - 파일 객체
     * @return DB에 저장할 파일 정보
     */
    public AttachVO uploadFile(final MultipartFile multipartFile) {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String mapperPath = "/attach/" + today + "/" + saveName;
        String uploadPath = getUploadPath(today) + "/" + saveName;
        File uploadFile = new File(uploadPath);

        try {
            multipartFile.transferTo(uploadFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return AttachVO.builder()
                .originalName(multipartFile.getOriginalFilename())
                .saveName(saveName)
                //.size(multipartFile.getSize())
                .path(mapperPath)
                .ext(saveName.substring(saveName.lastIndexOf(".")))
                .build();
    }
    
    /**
     * 저장 파일명 생성
     * @param filename 원본 파일명
     * @return 디스크에 저장할 파일명
     */
    private String generateSaveFilename(final String filename) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(filename);
        return uuid + "." + extension;
    }

 

    /**
     * 업로드 경로 반환
     * @param addPath - 추가 경로
     * @return 업로드 경로
     */
    private String getUploadPath(final String addPath) {
        return makeDirectories(uploadPath + "/" + addPath);
    }

    /**
     * 업로드 폴더(디렉터리) 생성
     * @param path - 업로드 경로
     * @return 업로드 경로
     */
    private String makeDirectories(final String path) {
        File dir = new File(path);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        return dir.getPath();
    }
    
    // 경로 삭제 기본키랑 구분코드 받아와서 패스 찾아서 삭제 
    public int deleteFiles(List<AttachVO> list){
         
    	for(AttachVO d : list) {
    		File file = new File(upload +"/" + d.getAttachPath());
            
        	if( file.exists() ){
        		file.delete();
        	} else {
        		System.out.println("==="+ file.getAbsolutePath()); //현재 실행 중인 Workding directory에 File에 전달한 경로를 조합하여 절대 경로를 리턴
        	}
    	}
    	return 0;
    }

}
