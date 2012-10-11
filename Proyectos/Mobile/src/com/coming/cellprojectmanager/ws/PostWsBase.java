package com.coming.cellprojectmanager.ws;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import android.content.Context;
import android.net.http.AndroidHttpClient;

public abstract class PostWsBase extends WsBase {
	
	public PostWsBase(Context ctx) {
		super(ctx);
	}

	@Override
	protected HttpResponse doCall(AndroidHttpClient httpClient, String url,
			String... params) {
		if(params.length == 0) {
			throw new IllegalArgumentException("params debe ser: [0] json a postear.");
		}
		String json = params[0];
		StringEntity entity = null;
		try {
			entity = new StringEntity(json,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json"); 
		httpPost.setHeader("Content-type", "application/json;charset=utf-8"); 
		httpPost.setEntity(entity);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
