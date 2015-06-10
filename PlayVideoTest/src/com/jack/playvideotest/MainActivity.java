package com.jack.playvideotest;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends Activity implements OnClickListener{

	private VideoView videoView;//视频组件
	private Button play;//播放
	private Button pause;//暂停
	private Button replay;//重新播放
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		play=(Button) findViewById(R.id.play);
		pause=(Button) findViewById(R.id.pause);
		replay=(Button) findViewById(R.id.replay);
		videoView=(VideoView) findViewById(R.id.vidvo_view);
		play.setOnClickListener(this);
		pause.setOnClickListener(this);
		replay.setOnClickListener(this);
		initVideoPath();
	}

	
	private void initVideoPath(){
		//sd卡目录下有一个视频文件movie.3gp
		File file=new File(Environment.getExternalStorageDirectory(),
				"movie.3gp");
		videoView.setVideoPath(file.getPath());//指定视频文件的路径
		
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
			if(!videoView.isPlaying()){
				videoView.start();//开始播放
			}
			break;
		case R.id.pause:
			if(videoView.isPlaying()){
				videoView.pause();;//暂停
			}
			break;
		case R.id.replay:
			if(!videoView.isPlaying()){
				videoView.resume();;//重头播放视频
			}
			break;
		}
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(videoView!=null){
			videoView.suspend();//将videoView所占用的资料释放掉
		}
	}

	
	
	
}
