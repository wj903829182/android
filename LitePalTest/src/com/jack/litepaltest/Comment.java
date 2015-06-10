package com.jack.litepaltest;

import java.util.Date;

import org.litepal.crud.DataSupport;

public class Comment extends DataSupport {

	private int id;
	private String content;
	private News news;
	private Date publishdate;

	// 自动生成get，set方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Date getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}

}
