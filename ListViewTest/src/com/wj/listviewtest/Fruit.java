package com.wj.listviewtest;

public class Fruit {

	private String name;//水果名
	private int imageId;//水果图片的资源id
	private String test;
	
	//无参构造函数
	public Fruit(){}
	//有参构造函数
	public Fruit(String name,int imageId){
		this.name=name;
		this.imageId=imageId;
	}
	
	public Fruit(String name, int imageId, String test) {
		super();
		this.name = name;
		this.imageId = imageId;
		this.test = test;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	
}
