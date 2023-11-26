package com.tenniswing.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //스프링 환경 세팅을 돕는 어노테이션
@EnableWebSecurity	//스프링 시큐리티 설정할 클래스라고 알려주는 어노테이션
public class SecurityConfig{

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			
			.authorizeRequests((requests) -> requests
				//.antMatchers("/admin/**").hasRole("ADMIN")
				//.antMatchers("/host/**").hasRole("HOST")
				.antMatchers("/mypage").hasRole("MEMBER")
				.antMatchers("/", "/**").permitAll()
//				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/loginform")	
				
				.permitAll()
			)
			.logout((logout) -> logout.permitAll()).csrf().disable();
		

		return http.build();
	}
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }


}
