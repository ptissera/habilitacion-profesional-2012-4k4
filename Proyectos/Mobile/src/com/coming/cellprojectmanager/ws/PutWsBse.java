package com.coming.cellprojectmanager.ws;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

import android.content.Context;
import android.net.http.AndroidHttpClient;

public abstract class PutWsBse extends WsBase {

	public PutWsBse(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected HttpResponse doCall(AndroidHttpClient httpClient, String url,
			String... params) {
		if(params.length == 0) {
			throw new IllegalArgumentException("params debe ser: [0] json a putearr.");
		}
		String json = params[0];
		StringEntity entity = null;
		try {
			entity = new StringEntity(json,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpPut httpPut = new HttpPut(url);
		httpPut.setHeader("Accept", "application/json"); 
		httpPut.setHeader("Content-type", "application/json;charset=utf-8"); 
		httpPut.setEntity(entity);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
