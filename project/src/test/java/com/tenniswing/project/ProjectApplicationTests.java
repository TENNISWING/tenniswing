package com.tenniswing.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;

/*
1. 단어의 크기가 2 이상인 경우를 필터링한다.
2. 모든 단어를 대문자로 변환한다.
3. 모든 단어를 앞글자만 잘라내어 변환한다.
4. 모든 단어를 스페이스로 구분한 하나의 문자열로 합친다.
 */

@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	MemberService empservice;

	//@Test
	void contextLoads() {
	}

	@Autowired
	StringEncryptor jasyptStringEncryptor;

	//@Test
	void contextLoad() {
		List<MemberVO> list = empservice.getMemberAll();
		assertTrue(!list.isEmpty());
	}
	
	private final List<String> words = Arrays.asList("TONY", "a", "hULK", "B", "america", "X", "nebula", "Korea");

    @Test
    public void wordProcessTest() {
    	Function<String, String> function = word -> word.toUpperCase();
    	
    	words.stream().forEach(s -> System.out.println(s));
    	words.stream().forEach(System.out::println);
    	
        String result = words.stream()
                .filter(w -> w.length() > 1)
                .map(s -> s.toUpperCase() )
                .map(w -> w.substring(0, 1))
                .collect(Collectors.joining(" "));

        assertEquals(result,"T H A N K");
    }
    
    @Test
    public void 메서드레퍼런수() {
    	//words.stream().
    }

	@Test
	void ramda() {
		List<Member> memberList = Arrays.asList(new Member("A"), new Member("B"), new Member("C"));
		String collect = memberList.stream().map(element -> element.getName()).collect(Collectors.joining());

		memberList.stream().forEach(System.out::println);
	}
	
	public static class Member {
		private String name;

		public Member(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
		
		public String toString(){
			return name;
		}
	}
}
