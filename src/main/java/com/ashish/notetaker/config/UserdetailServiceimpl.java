package com.ashish.notetaker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ashish.notetaker.dao.Userrepo;
import com.ashish.notetaker.entity.User;



public class UserdetailServiceimpl implements UserDetailsService {
	
	@Autowired
	private Userrepo userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userrepo.getUserByusername(username);
		if (user==null)
		{
			throw new UsernameNotFoundException("Could not Found User!!");
		}
		
		CustomUserDetail customUserDetail=new CustomUserDetail(user);
		
		
		return customUserDetail;
	}

}
