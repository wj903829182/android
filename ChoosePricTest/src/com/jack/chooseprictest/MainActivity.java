package com.jack.chooseprictest;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	public static final int TAKE_PHOTO=1;//拍照常量
	public static final int CROP_PHOTO=2;//裁剪常量
	private Button takePhoto;//拍照按钮
	private ImageView picture;//显示图片的组件
	private Uri imageUri;//图片的Uri
	private Button chooseFromAlbum;//选择照片按钮
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		takePhoto=(Button) findViewById(R.id.take_photo);//获取拍照组件
		picture=(ImageView) findViewById(R.id.picture);//获取图片组件
		//拍照按钮监听
		takePhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//创建File对象，用于存储拍照后的图片
				/*创建一个File对象，用于存储摄像头拍下的图片，这里把图片命名为out_image.jpg,
				 * 并将它存放在手机SD卡的根目录下，调用Environment的getExternalStorageDirectory()
				 * 方法获取到的就是SD卡的根目录。
				 * */
				File outputImage=new File(Environment.getExternalStorageDirectory(),
						"output_image.jpg");
				try{
				if(outputImage.exists()){
					outputImage.delete();//如果文件存在，则删除
				}
				outputImage.createNewFile();//创建一个新文件
				}catch(Exception e){
					e.printStackTrace();
				}
				
				/*
				 * 调用Uri的fromFile()方法将File对象转换成Uri对象，这个Uri对象标识着out_image.jpg这
				 * 张图片的唯一地址。
				 * */
				imageUri=Uri.fromFile(outputImage);
				/*
				 * 构造一个Intent对象，并将这个Intent的action指定为android.media.action.IMAGE_CAPTURE
				 * ，再调用Intent的putExtra()方法指定图片的输出地址，这里填入刚刚得到的Uri对象，最后调用
				 * startActivityForResult()来启动活动。由于使用的是一个隐式Intent，系统会找出能够响应这个
				 * Intent的活动去启动，这样照相机程序就会被打开，拍下照片就会输出到out_image.jpg中。
				 * */
				Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, TAKE_PHOTO);
			}
		});
		
		
		//从相册中选择照片
		chooseFromAlbum=(Button) findViewById(R.id.choose_from_album);
		chooseFromAlbum.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * 在按钮的点击事件里同样创建了一个File对象，用于存储从相册中选择的图片。然后
				 * 构建出一个Intent对象，并将它的action指定为android.intent.action.GET_CONTENT
				 * 。接着在给这个Intent对象设置一些必要的参数，包括是否允许缩放和裁剪，图片的输出位置。
				 * 最后调用startActivityForResult()方法就可以打开从相册程序选择照片了。
				 * */
				//创建File对象，用于存储选择的照片
				File outputImage=new File(Environment.getExternalStorageDirectory(),
						"output_image.jpg");
				
				try{
					if(outputImage.exists()){
						outputImage.delete();
					}
					outputImage.createNewFile();
				}catch(Exception e){
					e.printStackTrace();
				}
				
				imageUri=Uri.fromFile(outputImage);
				/*Intent intent=new Intent(Intent.ACTION_GET_CONTENT);*/
				Intent intent=new Intent("android.intent.action.GET_CONTENT");
				/*Intent intent=new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);*/
				intent.setType("image/*");
				/*intent.setDataAndType(imageUri, "image/*");*/
				intent.putExtra("crop", true);//允许裁剪
				intent.putExtra("scale", true);//允许缩放
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, CROP_PHOTO);
				
			}
			
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case TAKE_PHOTO:
			if(resultCode==RESULT_OK){
				/*
				 * 上面我们使用的是startActivityForResult()来启动活动，因此拍完照后会有结果
				 * 返回到onActivityResult()方法中。如果发现拍照成功，则会再次构建出一个
				 * Intent对象，并把它的action指定为com.android.camera.action.CROP。
				 * 这个Intent是用于对拍出的照片进行裁剪的，因为摄像头拍出的照片都比较大，而我们可能只
				 * 希望截取其中的一小部分。然后给这个Intent设置一些必要的属性，并再次调用
				 * startActivityForResult()来启动裁剪程序。裁剪完成后的照片同学会输出到
				 * out_image.jpg中。
				 * */
				Intent intent =new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(imageUri, "image/*");
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, CROP_PHOTO);//启动裁剪程序
			}
			break;
		case CROP_PHOTO:
			if(resultCode==RESULT_OK){
				/*
				 * 裁剪完成后，程序又回调到onActivityResult()方法中，这个时候我们就可以调用
				 * BitmapFactory的decodeStream()方法将output_image.jpg这张照片解析
				 * 成Bitmap对象，然后把它设置到ImageView中显示出来。
				 * */
				try{
					Bitmap bitmap=BitmapFactory.decodeStream(
							getContentResolver().openInputStream(imageUri));
					//为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
                    Bitmap smallBitmap = zoomBitmap(bitmap, 
                    		bitmap.getWidth() / 6, bitmap.getHeight() / 6);
                    bitmap.recycle();
					picture.setImageBitmap(smallBitmap);//
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			break;
		default:
			break;
		}
	}

	public Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);// 利用矩阵进行缩放不会造成内存溢出
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

	
	
}
