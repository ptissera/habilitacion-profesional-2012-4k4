package com.coming.cellprojectmanager.ws;

import android.content.Context;
import android.util.Log;

public class PostAcontecimientoWs extends PostWsBase {

	public PostAcontecimientoWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParamsOk(String... params) {
		boolean paramsAreOk = (params.length == 1);
		if(paramsAreOk == false) {
			String msg = "Parametros invalidos. Deben ser [0]: acontecimeinto en json a postear.";
			Log.e("PostAcontecimientoWs", msg);
		}
		return paramsAreOk;
	}

	@Override
	protected String doFakeCall(String... params) {
		// TODO Auto-generated method stub
		return "{" +
				"\"error\":{\"codigo\":0,\"descripcion\":\"Exito\"}" +
				"}";
	}

	@Override
	protected String buildUrl(String... params) {
		String url = "http://localhost/projectcellmanager/webservices/acontecimiento";
		return url;
	}

}
