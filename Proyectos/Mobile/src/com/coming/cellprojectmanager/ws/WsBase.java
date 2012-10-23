package com.coming.cellprojectmanager.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.params.HttpConnectionParams;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

public abstract class WsBase extends AsyncTask<String, Void, String> {
	private static boolean fakeWs = false;
	private WsObserver observer;
	protected Context context;
	
	public WsBase(Context ctx) {
		context = ctx;
	}
	
	protected abstract boolean checkParamsOk(String... params);
	protected abstract String doFakeCall(String... params);
	protected abstract String buildUrl(String... params);
	protected abstract HttpResponse doCall(AndroidHttpClient httpClient,
			String url, String... params);
	protected abstract boolean validResponseCode(int responseCode);
	
	public void execute(WsObserver observer, String... params) {
		this.observer = observer;
		super.execute(params);
	}
	
	@Override
	protected void onPreExecute() {
		if(observer != null) {
			observer.notifiyPreExecute();
		}
	}
		
	@Override
	protected String doInBackground(String... params) {
		if(!checkParamsOk(params)) {
			throw new InvalidParameterException("Ws Invalid parameters: " + params.toString());
		}
		String result = null;
		if(fakeWs) {
			return doFakeCall(params);
		}
		String url = buildUrl(params);
		Log.i("WsBase", url);
		AndroidHttpClient httpClient = AndroidHttpClient.newInstance("projectcellmanagermobile-v" + getAppVersion());
		HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 120000);
		HttpConnectionParams.setSoTimeout(httpClient.getParams(), 120000);
		HttpResponse response = doCall(httpClient, url, params);
		int responseStatusCode = -1;
		if(response != null) {
			responseStatusCode = response.getStatusLine().getStatusCode();
		}
		if(validResponseCode(responseStatusCode)) {
			StringBuilder builder = new StringBuilder();
			InputStream is = null;
			BufferedReader reader = null;
			HttpEntity entity = response.getEntity();
			if(entity != null) {
				try { 
					is = entity.getContent();
					reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
					String line = null;
					while((line = reader.readLine()) != null) {
						builder.append(line + "\n");	
					}
					result = builder.toString();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}			
		httpClient.close();
		
		if(result == null || result.isEmpty()) {
			result = "{\"error\":{\"codigo\":-1,\"descripcion\":\"Error en la coneccion.\"}}";
		}
		Log.i("WsBase", responseStatusCode + " " + result);
		
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		if(observer != null) {
			observer.notifiyPosExecute(result);
		}		
	}
	
	private String getAppVersion() {
		PackageManager pkgMan = context.getPackageManager();
		PackageInfo info = null;
		String version = "unknown";
		try {
			info = pkgMan.getPackageInfo(context.getPackageName(), 0);
			version = String.valueOf(info.versionCode);
		} catch (NameNotFoundException e1) {
			e1.printStackTrace();
		}
		return version;
	}	
}