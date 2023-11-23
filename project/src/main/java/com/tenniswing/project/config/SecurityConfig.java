package com.tenniswing.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //스프링 환경 세팅을 돕는 어노테이션
@EnableWebSecurity	//스프링 시큐리티 설정할 클래스라고 알려주는 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable() // POST방식 허용
                .authorizeRequests()
                	.antMatchers("/", "/login", "/signup", "/home","/signup","/signup").permitAll() // 이 URI는 누구든 접근가능
                	.antMatchers("/admin/**").hasRole("ADMIN") // ADMIN role만 접근 가능
                	.antMatchers("/host/**").hasRole("HOST") // ADMIN role만 접근 가능
//                	.anyRequest().authenticated() // 어떤 URI로 접근하던 인증이 필요함
                .and()
                    .formLogin()
                    .loginPage("/loginform")
                    .loginProcessingUrl("/loginProc") // 이 URI 호출시 스프링 시큐리티로 폼 정보를 제출 / form의 action
                    .usernameParameter("memid") // 폼 input name값: default - username
                    .passwordParameter("pwd") // 폼 input name값: default - password
//                    .successHandler(loginSuccessHandler()) // 로그인 성공을 다룰 핸들러
//                    .failureHandler(loginFailHandler()) // 로그인 실패를 다룰 핸들러
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/") // 로그아웃 성공시 이동할 URL
        			.logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc")); // 이 URI 호출시 로그아웃
    }
	
//	.antMatchers()
//	: 페이지에 접근할 수 있는 권한을 설정한다.
//	.loginPage
//	: 로그인 페이지
//	.loginProcessingUrl
//	: 구현한 로그인 페이지
//	defaultSuccessUrl
//	: 로그인 성공 시 제공할 페이지
//	failureUrl
//	: 로그인 실패 시 제공할 페이지
//	csrf().disable()
//	: 사이트 간 요청 위조(Cross-Site Request Forgery) 공격 방지 기능 키기
    
    //인증 예외 추가
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
    }
    
    /**
     * 로그인 인증 처리 메소드
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
	
}
