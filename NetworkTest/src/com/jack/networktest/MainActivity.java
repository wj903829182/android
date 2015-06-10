package com.jack.networktest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	public static final int SHOW_RESPONSE=0;
	private Button sendRequest=null;
	private TextView responseText=null;
	
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what){
			case SHOW_RESPONSE:
				String response=(String) msg.obj;
				//在这里进行UI操作，将结果显示到界面上
				responseText.setText(response);
				break;
			default:
				break;
			}
			
		}
		
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sendRequest=(Button) findViewById(R.id.send_request);
		responseText=(TextView) findViewById(R.id.response_text);
		sendRequest.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.send_request){
			//sendRequestWithHttpURLConnection();
			sendRequestWithHttpClient();
		}
		
	}
	
	
	private void sendRequestWithHttpURLConnection(){
		//开启线程来发起网络请求
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpURLConnection connection=null;
				
				try {
					URL url=new URL("http://www.baidu.com");
					connection =(HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in=connection.getInputStream();
					//下面对获取到的输入流进行读取
					BufferedReader reader=new BufferedReader(new InputStreamReader(in));
					StringBuilder response=new StringBuilder();
					String line;
					while((line=reader.readLine())!=null){
						response.append(line);
					}
					Message message=new Message();
					message.what=SHOW_RESPONSE;
					//将服务器返回的结果存放到Message中
					message.obj=response.toString();
					handler.sendMessage(message);
					
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(connection!=null){
						connection.disconnect();
					}
				}
			}
			
		}).start();
		
		
	}
	
	
	private void sendRequestWithHttpClient(){
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					HttpClient httpClient=new DefaultHttpClient() ;
					//HttpGet httpGet=new HttpGet("http://www.baidu.com");
					//指定访问的服务器地址是电脑本机，10.0.2.2对模拟器来说就是电脑本机的ip地址
					//8080为端口号
					//HttpGet httpGet=new HttpGet("http://10.0.2.2:8080/get_data.xml");
					HttpGet httpGet=new HttpGet("http://10.0.2.2:8080/get_data.json");
					HttpResponse httpResponse=httpClient.execute(httpGet);
					if(httpResponse.getStatusLine().getStatusCode()==200){
						//请求和响应都成功了
						HttpEntity entity=httpResponse.getEntity();
						String response=EntityUtils.toString(entity,"utf-8");
						//调用parseXMLWithPull方法解析服务器返回的数据
						//parseXMLWithPull(response);
						
						//调用parseXMLWithSAX方法解析服务器返回的数据
						//parseXMLWithSAX(response);
						
						//调用parseJSONWithJSONObject方法解析服务器返回的数据
						//parseJSONWithJSONObject(response);
						
						
						
						//调用parseJSONWithGSON方法解析服务器返回的数据
						parseJSONWithGSON(response);
						
						Message message=new Message();
						message.what=SHOW_RESPONSE;
						//将服务器返回的结果存放到Message中
						message.obj=response.toString();
						handler.sendMessage(message);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}).start();
		
	}
	
	//使用Pull解析xml
	private void parseXMLWithPull(String xmlData){
		//Log.d("MainActivity", "parseXMLWithPull(String xmlData)");
		try{
			//获取到XmlPullParserFactory的实例，并借助这个实例得到XmlPullParser对象
			XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser=factory.newPullParser();
			//调用XmlPullParser的setInput方法将服务器返回的xml数据设置进去开始解析
			xmlPullParser.setInput(new StringReader(xmlData));
			//通过getEventType()方法得到当前解析事件
			int eventType=xmlPullParser.getEventType();
			String id="";
			String name="";
			String version="";
			while(eventType!=XmlPullParser.END_DOCUMENT){
				//通过getName()方法得到当前节点的名字，如果发现节点名等于id、name、或version
				//就调用nextText()方法来获取结点具体的内容，每当解析完一个app结点就将获取到的内容打印出来
				String nodeName=xmlPullParser.getName();
				//Log.d("MainActivity",""+eventType+ " nodeName= "+nodeName);
				switch(eventType){
				//开始解析某个节点
				case XmlPullParser.START_TAG:{
					if("id".equals(nodeName)){
						id=xmlPullParser.nextText();
					}else if("name".equals(nodeName)){
						name=xmlPullParser.nextText();
					}else if("version".equals(nodeName)){
						version=xmlPullParser.nextText();
					}
					break;
				}
				case XmlPullParser.END_TAG:{
					if("app".equals(nodeName)){
						Log.d("MainActivity", "id is "+id);
						Log.d("MainActivity", "name is "+name);
						Log.d("MainActivity", "version is "+version);
					}
					break;
				}
				default:
					break;
				}
				//调用next()方法获取到下一个解析事件
				eventType=xmlPullParser.next();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//进行SAX解析的函数
	private void parseXMLWithSAX(String xmlData){
		/*
		 * parseXMLWithSAX方法中先创建一个SAXParserFactory的对象，然后再获取到
		 * XMLReader对象，接着将我们编写的ContentHandler的实例设置到XMLReader中，
		 * 最后调用parse()方法开始执行解析。
		 * */
		try{
			SAXParserFactory factory=SAXParserFactory.newInstance();
			XMLReader xmlReader=factory.newSAXParser().getXMLReader();
			ContentHandler handler=new ContentHandler();
			//将ContentHandler的实例设置到XMLReader中
			xmlReader.setContentHandler(handler);
			//开始执行解析
			xmlReader.parse(new InputSource(new StringReader(xmlData)));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	//使用JSONObject解析json格式的数据
	private void parseJSONWithJSONObject(String jsonData){
		/*
		 * 首先把http请求的地址改成http://localhost:8080/get_data.json，然后在得到服务器返回
		 * 的数据后调用parseJSONWithJSONObject()方法来解析数据。由于我们在服务器中定义的是一个json数组，
		 * 因此解析的时候首先是将服务器返回的数据传入到一个JSONArray对象中。然后循环遍历这个JSONArray，从
		 * 中取出的每一个元素都是一个JSONObject对象，每个JSONObject对象中又包含了id,name和version这些
		 * 数据。接下来只需要调用getString()方法将这些数据取出，并打印出来即可。
		 * */
		try{
			JSONArray jsonArray=new JSONArray(jsonData);
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject=jsonArray.getJSONObject(i);
				String id=jsonObject.getString("id");
				String name=jsonObject.getString("name");
				String version=jsonObject.getString("version");
				Log.d("MainActivity", "id is "+id);
				Log.d("MainActivity", "name is "+name);
				Log.d("MainActivity", "version is "+version);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	//使用JSONWithGSON(String jsonData)方法解析json格式的数据
	private void parseJSONWithGSON(String jsonData){
		Gson gson=new Gson();
		List<App> appList=gson.fromJson(jsonData, new 
				TypeToken<List<App>>() {}.getType());
		for(App app: appList){
			Log.d("MainActivity", "id is "+app.getId());
			Log.d("MainActivity", "name is "+app.getName());
			Log.d("MainActivity", "version is "+app.getVersion());
		}
	}
	
	
	
}

