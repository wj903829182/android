package com.jack.networktest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	public static void sendHttpRequest(final String address,
			final HttpCallbackListener listener){
		new Thread(new Runnable(){

			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpURLConnection connection=null;
				try{
					URL url=new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					connection.setDoInput(true);
					connection.setDoOutput(true);
					InputStream in=connection.getInputStream();
					BufferedReader reader=new BufferedReader(new InputStreamReader(in));
					StringBuilder response=new StringBuilder();
					String line;
					while((line=reader.readLine())!=null){
						response.append(line);
					}
					if(listener!=null){
						//回调onFinish()方法
						listener.onFinish(response.toString());
					}
				}catch(Exception e){
					if(listener!=null){
						//回调onError()方法
						listener.onError(e);
					}
					
				}finally{
					if(connection!=null){
						connection.disconnect();
					}
					}
			}
				}).start();
		
		
	}
}

/*
我们首先给sendHttpRequest方法添加了一个HttpCallbackListener参数，并在方法的内部开启了一个子线程，然后
在子线程里去执行具体的网络操作。注意子线程中是无法通过return语句来返回数据的，因此这里我们将服务器响应的数据
传入了HttpCallbackListener的onFinish()方法中，如果出现了异常就将异常原因传入到onError()方法中。
  现在sendHttpRequest方法接收两个参数了，因此我们在调用它的时候还需要将HttpCallbackListener的实例传入
  如下所示：
  HttpUtil.sendHttpRequest(address,new HttpCallbackListener(){
   public void onFinish(String response){
   //在这里根据返回内容执行具体的逻辑
   }
   
   public void onError(Exception e){
      //在这里对异常进行处理
   }
   
  });
  
  这样的话，当服务器成功响应的时候我们就可以在onFinish方法里对响应数据进行处理了，类似地，如果出现了异常，就
  可以在onError方法里对异常情况进行处理。如此一来，我们就巧妙的利用回调机制将响应数据成功返回给调用方了。
  另外需要注意的是，onFinish方法和onError方法最终还是在子线程中运行的，因此我们不可以在这里执行任何的
  UI操作，如果需要根据返回的结果来更新UI，则仍然要使用异步消息处理机制。
 */
