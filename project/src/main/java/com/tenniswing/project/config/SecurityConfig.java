package com.tenniswing.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 스프링 환경 세팅을 돕는 어노테이션
@EnableWebSecurity // 스프링 시큐리티 설정할 클래스라고 알려주는 어노테이션
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()


				//여기에 로그인 필요한 페이지 적어주세요.
				.antMatchers("/mypage/**","/clubform").authenticated()
				//.antMatchers("/admin**").hasRole("ADMIN")
				//.antMatchers("/host**").hasRole("HOST")
				.anyRequest().permitAll()
				
				.and()
				
				.formLogin()
				.loginPage("/loginform")
				.loginProcessingUrl("/loginProc")
				.usernameParameter("memId")
				.passwordParameter("pwd")
				.defaultSuccessUrl("/")
			
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/");
				
//				.and()
//				.sessionManagement((auth) -> auth
//						//하나의 아이디에 대한 다중 로그인 허용 개수
//						.maximumSessions(5)
//						//다중 로그인 개수를 초과하였을 경우 처리 방법
//						.maxSessionsPreventsLogin(true))				
//				.sessionManagement((auth) -> auth
//						.sessionFixation().changeSessionId());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
