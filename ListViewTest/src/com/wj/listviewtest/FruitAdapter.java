package com.wj.listviewtest;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitAdapter extends ArrayAdapter<Fruit> {

	private int resourceId;
	public FruitAdapter(Context context, int textViewResourceId,
			List<Fruit> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		/*
		 * 重写了父类的构造函数，用于将上下文，ListView子项布局的id和数据都传进来。
		 * */
		resourceId=textViewResourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return super.getView(position, convertView, parent);
		/*
		 * 重写getView方法，这个方法在每个子项被滚动到屏幕内的时候会被调用，在getView方法中
		 * ，首先通过getItem方法得到当前项的Fruit实例，然后使用LayoutInflater来为这个子项加载
		 * 我们传入的布局，接着调用View的findViewById方法分别获取到ImageView和TextView的实例，
		 * 并分别调用他们的setImageResource和setText方法来设置显示的图片和文字，最后返回布局
		 * */
		Fruit fruit=getItem(position);//获取当前项的Fruit实例
		View view;
		ViewHolder viewHolder;
		/*
		 * 在getView()方法中进行判断，如果convertView为空，则使用LayoutInflater去加载布局，
		 * 如果不为空，则直接对convertView进行重用。这样可以大大提升ListView的效率，在快速滚动的时候
		 * 也可以表现更好的性能。
		 * */
		if(convertView==null){
			//初始话ListView的子项布局
			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
		    viewHolder=new ViewHolder();
		    viewHolder.fruitImage=(ImageView) view.findViewById(R.id.fruit_image);
		    viewHolder.fruitName=(TextView) view.findViewById(R.id.fruit_name);
		    viewHolder.test=(TextView) view.findViewById(R.id.fruit_test);
		    view.setTag(viewHolder);//将ViewHolder存储在View中
		}else{
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();//重新获取ViewHolder
		}
		/*//初始话ListView的子项布局
		View view=LayoutInflater.from(getContext()).inflate(resourceId, null);*/
		/*ImageView fruitImage=(ImageView) view.findViewById(R.id.fruit_image);
		TextView fruitName=(TextView) view.findViewById(R.id.fruit_name);*/
		viewHolder.fruitImage.setImageResource(fruit.getImageId());
		viewHolder.fruitName.setText(fruit.getName());
		 viewHolder.test.setText(fruit.getTest());
		return view;
	}
	
	
	class ViewHolder{
		ImageView fruitImage;
		TextView  fruitName;
		TextView  test;
	}

}
