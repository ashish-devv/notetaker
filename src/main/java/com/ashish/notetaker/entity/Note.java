package com.ashish.notetaker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int nid;
	
	@Column(nullable = false)
	private int uid;
	
	@Column(nullable = false)
	private String note_heading;
	
	@Column(nullable = false,length = 10000,columnDefinition="MEDIUMTEXT")
	private String note_content;
	
	@Column(nullable = false,updatable=false)
	@CreationTimestamp
	private Date note_date;
	
	

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNote_heading() {
		return note_heading;
	}

	public void setNote_heading(String note_heading) {
		this.note_heading = note_heading;
	}

	public String getNote_content() {
		return note_content;
	}

	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}

	public Date getNote_date() {
		return note_date;
	}

	public void setNote_date(Date note_date) {
		this.note_date = note_date;
	}

	@Override
	public String toString() {
		return "Note [nid=" + nid + ", uid=" + uid + ", note_heading=" + note_heading + ", note_content=" + note_content
				+ ", note_date=" + note_date + "]";
	}

	public Note(int nid, int uid, String note_heading, String note_content, Date note_date) {
		super();
		this.nid = nid;
		this.uid = uid;
		this.note_heading = note_heading;
		this.note_content = note_content;
		this.note_date = note_date;
	}

	public Note() {
		super();
	}
	
	
	
}
