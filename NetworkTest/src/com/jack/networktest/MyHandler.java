package com.jack.networktest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
	}

}
/*
 startDocument()方法会在开始xml解析的时候调用，startElement方法会在开始解析某个节点的时候调用，
 characters方法会在获取节点中内容的时候调用，endElement方法会在完成解析某个结点的时候调用，endDocument()
 方法会在完成xml解析的时候调用。其中，startElement， characters和endElement这三个方法是有参数的，从xml中
 解析出的数据就会以参数的形式传入到这些方法中。需要注意的是，在获取结点中内容时，characters方法可能
 会被调用多次，一些换行符也被当作内容解析出来，我们需要针对这种情况在代码中做好控制。
    那么下面就让我们尝试调用SAX解析的方式来实现和前面博客同样的功能。新建一个ContentHandler类继承自
   DefaultHandler，并重写父类的五个方法，如下所示： 

*/