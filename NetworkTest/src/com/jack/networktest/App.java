package com.jack.networktest;

public class App {

	private String id;
	private String name;
	private String version;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
}

/*
   谷歌提供的GSON开源库可以让解析JSON数据的工作简单到不敢想象的地步。要使用GSON必须要在项目中添加一个
   GSON的jar包。首先需要将GSON的资源压缩包下载下来，下载地址是：htt://code.google.com/p/google-gson/downloads/list
   然后把下载下来的gson-x.x.x.jar包拷贝到NetworkTest项目的libs目录下，GSON库就自动添加到项目了。
   GSON的强大之处，在于可以将一段json格式的字符串自动映射成一个对象，从而不需要我们再动手去编写代码进行解析了。
   比如说一段json格式的数据如下所示：
   {"name":"tom","age":"20"}
   那么我们就可以定义一个Person类，并加入name和age这两个字段，然后只需简单地调用如下代码就可以将json数据
   自动解析成一个Person对象了：
   Gson gson=new Gson();
   Person person=gson.fromJson(jsonData,Person.class);
   如果需要解析的是一段json数组会稍微麻烦点，我们需要借助TypeToken将期望解析的数据类型传入到fromJson()方法，
   如下所示：
   List<Person> people=gson.fromJson(jsonData,new TypeToken<List<Person>>(){}.getType());
   好了，基本的用法就是这样了，下面我们来试试，首先新增一个App类，并加入id，name和version这三个字段，如下所示：
   
 * */


