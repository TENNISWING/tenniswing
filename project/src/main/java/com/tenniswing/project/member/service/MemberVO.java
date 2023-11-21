package com.tenniswing.project.member.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO {
	// 1) 컬럼따라 타입을 구분 or 실제 타입 대신 String
	// 2) 기본타입(int, double 등) or Warp 클래스(Integer, Double)
	private int employeeId; /* private Integer employeedId; */
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
	private String jobId;
	private double salary;
	private Double commissionPct;
	private Integer managerId;
	private Integer departmentId;
}

