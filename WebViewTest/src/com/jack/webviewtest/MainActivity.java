package com.jack.webviewtest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	private WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获得WebView的实例
		webView=(WebView) findViewById(R.id.web_view);
		//调用getSettings()设置一些浏览器的属性，调用setJavaScriptEnabled方法来让WebView支持javascript脚本
		webView.getSettings().setJavaScriptEnabled(true);
		/*
		 * 调用setWebViewClient()方法，并传入了WebViewClient的匿名类作为参数，然后重写了
		 * shouldOverrideUrlLoading方法，这就表明当需要从一个网页跳转到另一个网页时，我们希望目标
		 * 网页仍然在当前WebView中显示，而不是打开系统浏览器。
		 * */
		webView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);//根据传入的参数在去加载新的网页
				return true;//表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
			}
			
		});
		//调用loadUrl()方法，并将网址传入，即可展示相应的网页内容
		webView.loadUrl("http://www.baidu.com");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
