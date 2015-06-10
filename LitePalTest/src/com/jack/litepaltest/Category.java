package com.jack.litepaltest;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

public class Category extends DataSupport {

	private int id;
	private String name;
	private List<News> newsList = new ArrayList<News>();

	// 自动生成get和set方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
