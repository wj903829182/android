package com.wj.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
/*
 * 在活动的管理中我们，通过一个List来暂存活动，然后提供了一个addActivity(Activity activity)
 * 方法用于向List中添加活动，提供了一个removeActivity(Activity activity)方法用于从List中
 * 移除活动，提供了一个finishAll()方法用于将存储的活动全部都销毁掉。
 * */
public class ActivityCollector {

	public static List<Activity> activities=new ArrayList<Activity>();
	//添加活动
	public static void addActivity(Activity activity){
		activities.add(activity);
	}
	
	//删除活动
	public static void removeActivity(Activity activity){
		activities.remove(activity);
	}
	
	public static void finishAll(){
		for(Activity activity:activities){
			if(!activity.isFinishing()){
				activity.finish();
			}
		}
	}
	
	
}
