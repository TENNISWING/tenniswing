package com.tenniswing.project.config;

import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilePathConfig implements WebMvcConfigurer{
//	@Value("${lodingPath}")
//	String lodingPath;
	private final String lodingPath = Paths.get("C:", "attach").toString();

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// addResourceHandler("매핑 경로")를 적어둔다. localhost:8080/upload/ 로 들어오는 모든 정적 리소스
		// 요청을 static폴더가 아닌 .addResourceLoactions에 적어둔 경로로 부터 찾아준다.
		registry.addResourceHandler("/attach/**").addResourceLocations(lodingPath);
	}
}
