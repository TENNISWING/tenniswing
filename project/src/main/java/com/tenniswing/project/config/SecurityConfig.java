package com.tenniswing.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration 
@EnableWebSecurity 
public class SecurityConfig {
	
	@Autowired
	AuthenticationFailureHandler customFailureHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http	.csrf().disable()
				
				//경로 권한 설정
				.authorizeRequests()				
				.antMatchers("/mypage/**","/clubform","/clubdetail","/matchregi","/snsRegister"
						,"/reserveCourt","/snsEditForm","/snsMyList","/noticeForm","/checkout"
						,"/noticeForm","/noticeEditForm","/snsMyList").authenticated()		
				.antMatchers("/admin**").hasRole("ADMIN")
				.antMatchers("/host**").hasRole("HOST")				
				
				//권한 없는 접근 처리
				.anyRequest().permitAll()
				
				//로그인 설정
				.and().formLogin()
				.loginPage("/loginform")
				.loginProcessingUrl("/loginProc")
				.usernameParameter("memId")
				.passwordParameter("pwd")
				.failureHandler(customFailureHandler)
				.defaultSuccessUrl("/")
				
				//로그 아웃 설정
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/");				

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
