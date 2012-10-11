package com.coming.cellprojectmanager.ws;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.content.Context;
import android.net.http.AndroidHttpClient;

public abstract class GetWsBase extends WsBase {

	public GetWsBase(Context ctx) {
		super(ctx);
	}

	@Override
	protected HttpResponse doCall(AndroidHttpClient httpClient, String url, String... params) {
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
