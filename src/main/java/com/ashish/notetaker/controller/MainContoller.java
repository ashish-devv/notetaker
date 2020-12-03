package com.ashish.notetaker.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ashish.notetaker.dao.Userrepo;
import com.ashish.notetaker.entity.User;

@Controller
public class MainContoller {
	
	@Autowired
	Userrepo userrepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	@GetMapping("/")
	public String home()
	{
		return "index";
	}
	
	@RequestMapping("/signin")
	public String signin()
	{
		return "login";
	}
	
	
	@GetMapping("/signup")
	public String signup(Model model)
	{
		
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String registeruser(@ModelAttribute("user") User user,Model model,HttpSession httpSession)
	{
		try {
			User check=this.userrepo.getUserByusername(user.getEmail());
			System.out.println(check);
			if(check!=null)
			{
				throw new Exception("Already registered with Email Entered By You");
			}
			user.setEnabled(true);
			user.setRole("ROLE_USER");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User u=this.userrepo.save(user);
			model.addAttribute("user", u);
			httpSession.setAttribute("message", "You are Registered Successfully! ✔");
			return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			httpSession.setAttribute("message", e.getMessage());
			return "signup";
		}
		
		
	}

}
