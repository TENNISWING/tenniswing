package com.tenniswing.project;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;

@SpringBootTest
class ProjectApplicationTests {
	
	@Autowired
	MemberService empservice;
	
	@Test
	void contextLoads() {
	}
	@Autowired
	StringEncryptor jasyptStringEncryptor;
		
	@Test
	void contextLoad() {
		List<MemberVO> list = empservice.getMemberAll();
		assertTrue(!list.isEmpty());
	}
}
