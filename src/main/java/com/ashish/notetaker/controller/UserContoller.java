package com.ashish.notetaker.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ashish.notetaker.dao.Noterepo;
import com.ashish.notetaker.dao.Userrepo;
import com.ashish.notetaker.entity.Note;
import com.ashish.notetaker.entity.User;

@Controller
@RequestMapping("/user")
public class UserContoller {
	
	
	@Autowired
	Userrepo userrepo;
	
	@Autowired
	Noterepo noterepo;
	
	@GetMapping("/")
	public String home(Principal principal,HttpSession httpSession,Model model)
	{
		String email=principal.getName();
		User user=this.userrepo.getUserByusername(email);
		List<Note> list=this.noterepo.getallnotes(user.getUid());
		httpSession.setAttribute("user", user);
		model.addAttribute("list", list);
		model.addAttribute("user", user);
		return "user-home";
	}
	
	@GetMapping("/addnote")
	public String addnote(Model model)
	{
		model.addAttribute("note",new Note());
		return "add-note";
	}
	
	@PostMapping("/add")
	public String addnotepost(@ModelAttribute("note") Note note,HttpSession httpSession)
	{
		try {
			User user=(User)httpSession.getAttribute("user");
			if(user==null)
			{
				throw new Exception("Some Error Occured");
			}
			note.setUid(user.getUid());
			this.noterepo.save(note);
			httpSession.setAttribute("message","Notes Added Successfully ✅");
			return "add-note";
		} catch (Exception e) {
			e.printStackTrace();
			httpSession.setAttribute("message","some problem is Occured ! Please Re-login. 💀");
			return "add-note";
		}
		
	}
	
	@GetMapping("/deletenote/{id}")
	public String deletenote(@PathVariable("id") int nid,HttpSession httpSession)
	{
		try {
			Note note=this.noterepo.getnotebyid(nid);
			if(note==null)
			{
				return "redirect:/user/";
			}
			User user =(User) httpSession.getAttribute("user");
			if(user.getUid()!=note.getUid())
			{
				return "redirect:/user/";
			}
			this.noterepo.deleteById(nid);
			return "redirect:/user/";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/user/";
		}
		
	}
	
	@GetMapping("/editnote/{id}")
	public String editpage(@PathVariable("id") int nid,HttpSession httpSession,Model model)
	{
		try {
			Note note=this.noterepo.getnotebyid(nid);
			if(note==null)
			{
				return "redirect:/user/";
			}
			User user =(User) httpSession.getAttribute("user");
			if(user.getUid()!=note.getUid())
			{
				return "redirect:/user/";
			}
			model.addAttribute("note", note);
			return "add-note";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/user/";
		}
	}
	
}
