package com.ashish.notetaker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashish.notetaker.entity.Note;

public interface Noterepo extends JpaRepository<Note, Integer>{

	@Query("select n from Note n where uid= :uid order by n.note_date desc")
	public List<Note> getallnotes(@Param("uid") int uid);
	
	@Query("select n from Note n where nid= :nid ")
	public Note getnotebyid(@Param("nid") int nid);
}
