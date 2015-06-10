package com.jack.playaudiotest;

import java.io.File;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button play;
	private Button pause;
	private Button stop;
	private MediaPlayer mediaPlayer=new MediaPlayer();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		play=(Button) findViewById(R.id.play);
		pause=(Button) findViewById(R.id.pause);
		stop=(Button) findViewById(R.id.stop);
		
		play.setOnClickListener(this);
		pause.setOnClickListener(this);
		stop.setOnClickListener(this);
		initMediaPlayer();//初始化MediaPlayer
	}

	
	private void initMediaPlayer(){
		try{
			/*
			 * sd卡的根目录下放置一个名为music.mp3的音频文件
			 * */
			File file=new File(Environment.getExternalStorageDirectory(),
					"music.mp3");
			mediaPlayer.setDataSource(file.getPath());//指定音频文件的路径
			mediaPlayer.prepare();//让MediaPlayer进入到准备状态
		}catch(Exception e){
			
		}
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
		switch(v.getId()){
		case R.id.play:
			if(!mediaPlayer.isPlaying()){
				mediaPlayer.start();//开始播放
			}
			break;
		case R.id.pause:
			if(mediaPlayer.isPlaying()){
				mediaPlayer.pause();;//暂停播放
			}
			break;
		case R.id.stop:
			if(mediaPlayer.isPlaying()){
				mediaPlayer.reset();//停止播放
				initMediaPlayer();//初始化
			}
			break;
		default:
			break;
		}
		
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mediaPlayer!=null){
			mediaPlayer.stop();
			mediaPlayer.release();
			//调用上面的两个方法后，将与mediaPlayer相关的资源释放掉了
		}
	}
	
	

}
