package com.tenniswing.project.court.service;
import lombok.Data;

@Data
public class TokenVO {
	private String imp_key;
	private String imp_secret;
	
	public TokenVO() {
		this.imp_key = "1400532585118642";
		this.imp_secret = "IlVyRYKzJ6Uby76JxBJ4tILjwg22tdaSW0DgDh3NXHQChzEbm5Xjnc0ynwmjyNmV7qB6YUUXOuxEc120";
	}
}
