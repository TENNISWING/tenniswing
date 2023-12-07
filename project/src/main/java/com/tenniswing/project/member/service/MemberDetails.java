package com.tenniswing.project.member.service;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class MemberDetails implements UserDetails{
	
	private MemberVO memberVO;
	
	
	public MemberDetails(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collection = new ArrayList<>();
		
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
            	
                return memberVO.getMemDiv();
            }
        });
        return collection;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return memberVO.getPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return memberVO.getMemId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		if(!memberVO.getState().equals("bh1")) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
