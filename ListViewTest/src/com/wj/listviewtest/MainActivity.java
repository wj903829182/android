package com.wj.listviewtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	/*private String [] data={"apple","banana","orange",
			"watermelon","pear","grape","pineapple","strawberry",
			"cherry","mango"};*/
	private List<Fruit> fruitList=new ArrayList<Fruit>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*//创建适配器
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(
				MainActivity.this,android.R.layout.simple_list_item_1,
				data);
		ListView listView=(ListView) findViewById(R.id.list_view);
		listView.setAdapter(adapter);*/
		initFruits();//初始化水果
		FruitAdapter adapter=new FruitAdapter(MainActivity.this,
				R.layout.fruit_item, fruitList);
		ListView listView=(ListView) findViewById(R.id.list_view);
		//设置适配器
		listView.setAdapter(adapter);
		/*
		 * setOnItemClickListener()方法来为ListView注册一个监听器，当用户点击了ListView
		 * 中的任何一个子项时就会回调nItemClick()方法，在这个方法中可以通过position参数判断出用户点击
		 * 的是哪一个子项，然后获取相应的水果，并通过Toast将水果的名字显示出来。
		 * */
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Fruit fruit=fruitList.get(position);
				Toast.makeText(MainActivity.this, 
						fruit.getName(), Toast.LENGTH_SHORT).show();
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void initFruits(){
		Fruit apple=new Fruit("apple",R.drawable.apple_pic,"dd");
		fruitList.add(apple);
		Fruit banana=new Fruit("banana",R.drawable.banana_pic,"dd");
		fruitList.add(banana);
		Fruit orange=new Fruit("orange",R.drawable.orange_pic,"dd");
		fruitList.add(orange);
		Fruit watermelon=new Fruit("watermelon",R.drawable.watermelon_pic,"dd");
		fruitList.add(watermelon);
		Fruit pear=new Fruit("pear",R.drawable.pear_pic,"dd");
		fruitList.add(pear);
		Fruit grape=new Fruit("grape",R.drawable.grape_pic,"dd");
		fruitList.add(grape);
		Fruit pineapple=new Fruit("pineapple",R.drawable.pineapple_pic,"dd");
		fruitList.add(pineapple);
		Fruit strawberry=new Fruit("strawberry",R.drawable.strawberry_pic,"dd");
		fruitList.add(strawberry);
		Fruit cherry=new Fruit("cherry",R.drawable.cherry_pic,"dd");
		fruitList.add(cherry);
		Fruit mango=new Fruit("mango",R.drawable.mango_pic,"dd");
		fruitList.add(mango);
	}
	
	

}
