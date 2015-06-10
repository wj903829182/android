package com.jack.networktest;

public interface HttpCallbackListener {

	void onFinish(String response);
	void onError(Exception e);
}
