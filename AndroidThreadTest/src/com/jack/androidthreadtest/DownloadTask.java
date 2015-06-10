package com.jack.androidthreadtest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;


/*
 AsyncTask的基本用法，由于AsyncTask是一个抽象类，所以如果我们想要使用它，就必须创建一个子类
 去继承它。在继承时我们可以为AsyncTask类指定3个泛型参数，这三个参数的用途如下。
 1.Params：在执行AsyncTask时需要传入的参数，可用于在后台任务中使用。
 2.Progress：后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位。
 3.Result：当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。
 因此，一个最简单的自定义AsyncTask就可以 写成如下形式：
class DownloadTask extends AsyncTask<Void, Integer, Boolean>{}
     这里我们把AsyncTask的第一个参数泛型指定为Void，表示在执行AsyncTask的时候不需要传入参数给后台
任务。第二个泛型参数指定为Integer，表示使用整形数据来作为进度显示单位。第三个参数指定为Boolean，则
表示使用布尔型数据来反馈执行结果。
  当然，目前我们自定义的DownloadTask还是一个空任务，并不能进行任何实践的操作，我们还需要去重写AsyncTask
  中的几个方法才能完成任务的定制。经常需要去重写的方法有一下四个。
 1.onPreExecute()：这个方法会在后台任务开始执行之前调用，用于进行一些界面上的初始化操作，比如显示
   一个进度对话框等。
 
 2.doInBackground(Void... params)：这个方法中的所有代码都会在子线程中运行，我们应该在这里去处理
    所有的耗时任务。任务一旦完成就可以通过return语句来将任务的执行结果返回，如果AsyncTask的第三个泛型
  参数指定的是void，就可以不返回任务执行结果。注意，在这个方法中是不可以进行UI操作的，如果需要更新UI元素
  ，比如说反馈任务的执行进度，可以调用publishProgress（Progress....）方法来完成。
 
 3.onProgressUpdate(Integer... values)：当在后台任务中调用publishProgress（Progress....）方法
    后，这个方法就会很快被调用，方法中携带的参数就是在后台任务中传递过来的。在这个方法中可以对UI进行操作，利用
  参数中的数值就可以对界面元素进行相应的更新。
 
 4.onPostExecute(Boolean result)：当后台任务执行完毕并通过return语句进行返回时，这个方法就很快
   会被调用。返回的数据会作为参数传递到此方法中，可以利用返回的数据来进行一些UI操作，比如说提醒任务执行的结果
   ，以及关闭掉进度条对话框等。
 
 
 
 */
public class DownloadTask /*extends AsyncTask<Void, Integer, Boolean>*/{

	/*@Override
	protected Boolean doInBackground(Void... params) {
		// TODO Auto-generated method stub
		try{
			while(true){
				int downloadPercent=doDownload();//这是一个虚构的方法
				publishProgress(downloadPercent);
				if(downloadPercent>=100){
					break;
				}
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		progressDialog.dismiss();//关闭进度对话框
		//在这里提示下载结果
		if(result){
			Toast.makeText(context, "download succeeded", 
					Toast.LENGTH_SHORT);
		}else{
			Toast.makeText(context, "download failed", 
					Toast.LENGTH_SHORT);
		}
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		ProgressDialog.show();//显示进度对话框
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		//在这里更新下载进度
		ProgressDialog.setMessage("downloaded"+values[0]+"%");
	}
*/
	
	
}

/*
   在这个DownloadTask中，我们在doInBackground()方法里执行具体的下载任务。这个方法里的代码都是在
   子线程中运行的，因而不会影响到主线程的运行。注意这里虚构了一个doDownload()方法，这个方法用于计算
   当前的下载进度并返回，我们假设这个方法已经存在。在得到了当前下载进度后，下面该考虑如何把它显示到界面
   上，由于doInBackground()方法是在子线程中运行的，在这里肯定不能进行UI操作，所以我们可以调用
   publishProgress()方法并将当前的下载进度传进来，这样onProgressUpdate()方法就会很快被调用，在
   这里就可以进行UI操作了。
       当下载完后，doInBackground()方法会返回一个布尔型变量，这样onPostExecute()方法就会很快被调用，
       这个方法也是在主线程中运行的。然后在这里我们会根据下载的结果来弹出相应的Toast提示，从而完成整个
 DownloadTask任务。
      简单来说，使用 AsyncTask的诀窍就是doInBackground()方法中去执行具体的耗时任务，在onProgressUpdate()
      方法中进行UI操作，在onPostExecute()方法中执行一些任务的收尾工作。
  
  如果想要启动这个任务，只需编写以下代码即可：
  new DownloadTask().execute();
  以上就是AsyncTask的基本用法，我们并不需要考虑什么异步消息机制，也不需要专门使用一个Handler来发送
  和接收消息，只需要调用一下publishProgress()方法就可以轻松地从子线程切换到UI线程了。
       
 */



