package com.jack.networktest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class ContentHandler extends DefaultHandler {

	private String nodeName;
	private StringBuilder id;
	private StringBuilder name;
	private StringBuilder version;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		//super.characters(ch, start, length);
		//根据当前的结点名判断将内容添加到哪一个StringBuilder对象中
		if("id".equals(nodeName)){
			id.append(ch,start,length);
			//Log.d("ContentHandler", "id.append(ch,start,length); "+id.toString().trim());
		}else if("name".equals(nodeName)){
			name.append(ch,start,length);
		}else if("version".equals(nodeName)){
			version.append(ch,start,length);
		}
	}
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		//super.endDocument();
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		//super.endElement(uri, localName, qName);
		if("app".equals(localName)){
			Log.d("ContentHandler", "id is "+id.toString().trim());
			Log.d("ContentHandler", "name is "+name.toString().trim());
			Log.d("ContentHandler", "version is "+version.toString().trim());
			//最后要将StringBuilder清空掉
			id.setLength(0);
			name.setLength(0);
			version.setLength(0);
			//Log.d("ContentHandler", "app app app app =  "+nodeName);
		}
		//Log.d("ContentHandler", "endElement  ="+localName);
	}
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		//super.startDocument();
		id=new StringBuilder();
		name=new StringBuilder();
		version=new StringBuilder();
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		//super.startElement(uri, localName, qName, attributes);
		nodeName = localName;//记录当前节点名
		//Log.d("ContentHandler", "startElement localname= "+localName);
	}
	
}

/*
我们首先给id，name和version结点分别定义了一个StringBuilder对象，并在startDocument方法里对
它们进行了初始化。每当开始解析某个结点的时候，startElement方法就会得到调用，其中localName参数记录着当前
结点的名字，这里我们把它记录下来。接着在解析结点中具体内容的时候就会调用characters方法，我们会根据当前结点名
进行判断，将解析出的内容添加到哪一个StringBuilder对象中。最后在endElement方法中进行判断，如果app结点已经解析完成，
就打印出id，name和version的内容。需要注意的是，目前id，name和version中都可能是包括回车或换行符的，因此
在我们打印之前我们还需要调用一下trim()方法，并且打印完成后还要将StringBuilder的内容清空掉，不然的话会影响
下一次内容的读取。
接下来的工作就是修改MainActivity中的代码，如下所示：
 * */



