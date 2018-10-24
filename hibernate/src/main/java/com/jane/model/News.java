package com.jane.model;

import java.sql.Blob;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class News {
	
	private Integer id; //field
	private String title;
	private String author;
	
	private Time time;
	
	private Timestamp timestamp;
	
	private java.sql.Date sqlDate;
 	
	private String describle;
	
	private Date date;
	
	//使用 title + "," + content 可以来描述当前的 News 记录. 
	//即 title + "," + content 可以作为 News 的 describle 属性值
	//大文本
	private String content;
	
	private Blob picture;
	
	public java.sql.Date getSqlDate() {
		return sqlDate;
	}
	
	public void setSqlDate(java.sql.Date sqlDate) {
		this.sqlDate = sqlDate;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public Time getTime() {
		return time;
	}
	
	public void setTime(Time time) {
		this.time = time;
	}
	
	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public Integer getId() { //property
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDescrible() {
		return describle;
	}
	public void setDescrible(String describle) {
		this.describle = describle;
	}
	
	public News(String title, String author, String describle, Date date, String content, Blob picture) {
		super();
		this.title = title;
		this.author = author;
		this.describle = describle;
		this.date = date;
		this.content = content;
		this.picture = picture;
	}

	public News() {
		// TODO Auto-generated constructor stub
	}

	public News(String title, String author, Date date) {
		super();
		this.title = title;
		this.author = author;
		this.date = date;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", author=" + author + ", time=" + time + ", timestamp="
				+ timestamp + ", sqlDate=" + sqlDate + ", describle=" + describle + ", date=" + date + ", content="
				+ content + ", picture=" + picture + "]";
	}
	
	
}
